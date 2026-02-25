package app.psn.mng.qest.vo;

import com.base.utl.SessionUtil;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 퀘스트 승인 처리
 */
@Setter
@Getter
@ToString
public class MngReqQuestProcVO {

	@NotNull(message = "퀘스트 수행 순번은 필수 값입니다.")
	@Min(value = 1, message = "퀘스트 수행 순번은 1 이상이어야 합니다.")
	private Integer logSn;

	@NotBlank(message = "상태값은 필수입니다.")
	@Pattern(regexp = "^(APPROVED|REJECTED)$", message = "입력값이 부적절 합니다.")
	private String status;

	private Integer processedBy = SessionUtil.getMngMberInfo().mberSn();

}