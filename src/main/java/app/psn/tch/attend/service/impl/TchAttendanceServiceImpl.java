package app.psn.tch.attend.service.impl;

import app.psn.std.quest.service.StdQuestService;
import app.psn.tch.attend.mapper.TchAttendanceMapper;
import app.psn.tch.attend.service.TchAttendanceService;
import app.psn.tch.attend.vo.StdAttendanceVO;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("tchAttendanceService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchAttendanceServiceImpl implements TchAttendanceService {

    private final TchAttendanceMapper tchAttendanceMapper;

    private final StdQuestService stdQuestService;

    @Override
    public List<String> sltMonthList() {
        return tchAttendanceMapper.sltMonthList();
    }

    @Override
    public List<String> sltWeekList(String month) {
        return tchAttendanceMapper.sltWeekList(month);
    }

    @Override
    public Integer sltAttendanceContinueCount() {

        return null;

    }

    @Override
    public void attendanceDo() {

        stdQuestService.questDo(StringUtil.setQuestPendingVO(2));

    }

}