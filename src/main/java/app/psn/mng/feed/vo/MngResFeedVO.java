package app.psn.mng.feed.vo;

import com.base.enumm.com.BbsTypeEnum;

/**
 * 게시물 정보
 */
public record MngResFeedVO( // 아바타 레벨 정보
                            Integer postSn,        // 게시물 번호
                            Integer bbsSn,         // 게시판 번호
                            BbsTypeEnum bbsType,   // 게시물 유형
                            String title,          // 제목
                            String subTitle,       // 서브 제목
                            String content,        // 내용
                            Integer viewCnt,       // 조회수
                            Integer creatMberSn,   // 작성자 순번
                            String creatMberNm,    // 작성자 이름
                            Integer udtMberSn,     // 수정자 순번
                            String udtMberNm,      // 수정자 이름
                            String useYn,          // 사용여부
                            String creatDate,      // 작성일
                            String udtDate         // 수정일
) {
}