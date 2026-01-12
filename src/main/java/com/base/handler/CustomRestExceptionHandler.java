package com.base.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.base.enumm.RstCdEnum;
import com.base.vo.BodyResVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler {

    private static final String ERROR_VIEW_PATH = "/error/errorPage";

    /**
     * Ajax 요청 여부 확인
     * 로직 단순화: 삼항 연산자 제거
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return request.getRequestURI().endsWith(".ax") 
            || "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * 공통 응답 처리 메소드
     * JSON(Ajax) 또는 View(JSP) 분기 처리
     */
    private Object resolveResponse(HttpServletRequest request, Model model, RstCdEnum rstCd, String message) {
        if (isAjaxRequest(request)) {
            BodyResVO error = new BodyResVO();
            error.setRtnCd(rstCd.getValue());
            error.setRtnMsg(message);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        // 일반 페이지 요청
        model.addAttribute("exception", message);
        return ERROR_VIEW_PATH;
    }

    // 1. 잘못된 형식의 Request Body 요청 (JSON 파싱 실패 등)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpServletRequest request, Model model) {
        return resolveResponse(request, model, RstCdEnum.fail, "Required request body is missing or invalid");
    }

    // 2. 필수 파라미터 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(HttpServletRequest request, Model model, MissingServletRequestParameterException ex) {
        String msg = ex.getParameterName() + " is missing";
        return resolveResponse(request, model, RstCdEnum.fail, msg);
    }

    // 3. 유효성 검증 실패 (@Valid)
    // 중요: MethodArgumentNotValidException은 BindException을 상속받으므로 BindException으로 통합 처리 가능
    @ExceptionHandler({BindException.class}) 
    public Object handleBindException(HttpServletRequest request, Model model, BindException ex) {
        
        // Ajax 요청: 첫 번째 에러 메시지만 반환 (또는 필요에 따라 전체 반환)
        if (isAjaxRequest(request)) {
            String firstErrorMsg = ex.getBindingResult().getAllErrors().stream()
                    .findFirst()
                    .map(e -> e.getDefaultMessage())
                    .orElse("Validation failed");
            
            log.debug("Ajax validation error: {}", firstErrorMsg);
            return resolveResponse(request, model, RstCdEnum.valid, firstErrorMsg);
        }

        // 일반 요청: 에러 필드명들을 쉼표로 연결
        String fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(","));
        
        log.debug("Page validation error fields: {}", fieldErrors);
        return resolveResponse(request, model, RstCdEnum.valid, fieldErrors);
    }

    // 4. 그 외 모든 예외
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public Object handleAllExceptions(HttpServletRequest request, Model model, Exception ex) {
        log.error("Unhandled Exception occurred: ", ex); // 전체 스택트레이스 로깅 (중요)

        // 보안상 내부 에러 메시지를 그대로 노출하기보다 일반적인 메시지를 권장하지만,
        // 개발 편의를 위해 유지한다면 아래와 같이 사용. 
        // 운영 배포시에는 "Internal Server Error" 등으로 변경하는 것이 좋습니다.
        String msg = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";
        
        return resolveResponse(request, model, RstCdEnum.fail, msg);
    }
}
