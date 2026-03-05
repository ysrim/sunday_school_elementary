package app.psn.std.home.vo;

/**
 * 공지사항
 */
public record StdNoticeVO( // 공지사항 정보
                           Integer postSn,           // 게시물 번호
                           String bbsType,           // 게시물 타입
                           String title,             // 제목
                           String subTitle,          // 서브제목
                           String content,           // 내용
                           Integer viewCnt,          // 조회수
                           String creatDate          // 작성 시간
) {
}