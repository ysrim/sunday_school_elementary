package app.psn.std.atnd.vo;

/**
 * 출석정보
 */
public record StdAtndVO( //
                         String date,       // 일자 (ex. YYYY-MM-DD)
                         String day,        // 일 (ex. DD)
                         Integer week,      // 주차 (ex. 1주차)
                         String status      // 출석상태('PENDING','APPROVED','REJECTED')
) {
}