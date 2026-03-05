package app.psn.mng.feed.vo;

import com.base.enumm.com.BbsTypeEnum;
import com.base.utl.SessionUtil;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시물 작성 정보
 */
@Setter
@Getter
@ToString
public class MngReqFeedVO {

	private Integer postSn;

	@NotNull(message = "게시물 정보가 없습니다!")
	@Positive(message = "게시물 정보가 없습니다!") // 양수만 가능
	private Integer bbsSn = 1; // 당분간 하드코드

	@NotEmpty(message = "제목을 입력해주세요!")
	private String title;

	private String content;

	@NotNull(message = "게시물 타입 정보가 없습니다!")
	private BbsTypeEnum bbsType;

	@NotEmpty(message = "게시물 사용여부를 입력해주세요!")
	private String useYn = "Y";

	private Integer creatMberSn = SessionUtil.getMngMberInfo().mberSn();

	private Integer udtMberSn = SessionUtil.getMngMberInfo().mberSn();

}