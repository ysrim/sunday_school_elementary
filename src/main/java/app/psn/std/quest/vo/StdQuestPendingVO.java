package app.psn.std.quest.vo;

import com.base.utl.SessionUtil;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StdQuestPendingVO {

	@NotNull(message = "퀘스트 번호는 필수입니다.")
	@Min(value = 1, message = "유효한 퀘스트 번호를 입력해주세요.")
	private Integer questSn; // 퀘스트_일련번호

	private Integer mberSn = SessionUtil.getStdMberInfo().mberSn(); // 회원_일련번호

	private Integer logSn;

}