package app.psn.com.vo;

/**
 * 방문로그
 */
public record VisitLogVO( //
                          Integer mberSn,    // 회원_일련번호
                          String reqUrl,     // 요청 URL
                          String userAgent   // agent
) {
}