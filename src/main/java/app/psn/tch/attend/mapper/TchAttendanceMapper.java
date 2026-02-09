package app.psn.tch.attend.mapper;

import app.psn.tch.attend.vo.StdAttendanceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TchAttendanceMapper {

    /**
     * 월 리스트
     */
    List<String> sltMonthList();

    /**
     * 월에 속한 리스트
     */
    List<String> sltWeekList(String month);

}