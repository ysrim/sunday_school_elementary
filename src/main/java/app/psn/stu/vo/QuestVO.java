package app.psn.stu.vo;

import com.base.utl.SessionUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuestVO {

	private String questSn; // 퀘스트_일련번호

	private String mberSn = SessionUtil.getMberInfo().getMberSn() + ""; // 회원_일련번호

}