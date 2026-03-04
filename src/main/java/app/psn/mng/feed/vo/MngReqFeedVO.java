package app.psn.mng.feed.vo;

import com.base.enumm.com.BbsTypeEnum;
import com.base.utl.SessionUtil;

import jakarta.validation.constraints.NotEmpty;
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

	@NotEmpty(message = "게시물 정보가 없습니다!")
	@Positive(message = "게시물 정보가 없습니다!") // 양수만 가능
	private Integer bbsSn;

	@NotEmpty(message = "제목을 입력해주세요!")
	private String title;

	private String content;

	@NotEmpty(message = "게시물 타입 정보가 없습니다!")
	private BbsTypeEnum bbsType;

	private Integer creatMberSn = SessionUtil.getMngMberInfo().mberSn();

	private Integer udtMberSn = SessionUtil.getMngMberInfo().mberSn();

}