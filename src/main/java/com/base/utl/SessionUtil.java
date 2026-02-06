package com.base.utl;

import java.util.Optional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.enumm.std.SessionKeyEnum;

import app.psn.std.login.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    private SessionUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 현재 스레드의 HttpServletRequest 객체를 안전하게 가져옵니다.
     */
    public static Optional<HttpServletRequest> getRequest() {

        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).map(ServletRequestAttributes::getRequest);

    }

    /**
     * 현재 세션을 가져옵니다. (존재하지 않으면 새로 생성하지 않음)
     */
    public static Optional<HttpSession> getSession() {

        return getRequest().map(request -> request.getSession(false));

    }

    /**
     * 세션에 객체를 저장합니다. (세션이 없으면 생성함)
     */
    public static void setAttribute(String name, Object value) {

        getRequest().ifPresent(request -> request.getSession(true).setAttribute(name, value));

    }

    /**
     * 세션에서 특정 객체를 제네릭하게 가져옵니다. (형변환 불필요)
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getAttribute(String name) {

        return getSession().map(session -> (T) session.getAttribute(name));

    }

    /**
     * 로그인된 회원 정보를 가져옵니다.
     */
    public static SessionVO getMberInfo() {

        return SessionUtil.<SessionVO>getAttribute(SessionKeyEnum.MBER_INFO.getKey()).orElse(null);

    }

    /**
     * 특정 세션 속성을 삭제합니다.
     */
    public static void removeAttribute(String name) {

        getSession().ifPresent(session -> session.removeAttribute(name));

    }

    /**
     * AJAX 요청 여부를 판별합니다.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        if (request == null) return false;

        String requestedWith = request.getHeader("X-Requested-With");
        String accept = request.getHeader("Accept");
        String uri = request.getRequestURI();

        return "XMLHttpRequest".equals(requestedWith) || (accept != null && accept.contains("application/json")) || (uri != null && uri.endsWith(".ax"));

    }

}