package app.psn.stu.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuestListVO {

	private String questSn; // 퀘스트_일련번호

	private String mberSn; // 회원_일련번호

	private String title; // 퀘스트_제목

	private String description; // 퀘스트_설명

	private String rewardExp; // 경험치

	private String rewardPoint; // 달란트

	private String questType; // 퀘스트_유형

	private String emoji; // 이모지

	private String status; // 요청_상태

	private String statusNm; // 요청_상태

}