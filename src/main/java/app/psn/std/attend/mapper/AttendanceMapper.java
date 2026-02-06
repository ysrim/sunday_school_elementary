package app.psn.std.attend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.attend.vo.AttendanceVO;

@Mapper
public interface AttendanceMapper {

    /**
     * 당월 주일 출석 목록
     */
    List<AttendanceVO> sltAttendanceList(Integer mberSn); // 출석 목록

    /**
     * 연속출석일 수
     */
    Integer sltAttendanceContinueCount(Integer mberSn);

}