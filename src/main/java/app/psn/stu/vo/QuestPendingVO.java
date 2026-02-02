package app.psn.stu.vo;

import com.base.utl.SessionUtil;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuestPendingVO {

	@NotEmpty(message = "퀘스트 일련번호를 입력해주세요!")
	private Integer questSn; // 퀘스트_일련번호

	private Integer mberSn = SessionUtil.getMberInfo().getMberSn(); // 회원_일련번호

}