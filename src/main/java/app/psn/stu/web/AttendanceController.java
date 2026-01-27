package app.psn.stu.web;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.idx.lgn.vo.LoginVO;
import app.psn.stu.service.AttendanceService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/std")
public class AttendanceController {

	@Resource(name = "attendanceService")
	private AttendanceService attendanceService;

	@MenuInfo(navi = NaviEnum.STD_ATND, role = MberGrdEnum.STD)
	@RequestMapping("/atnd.pg")
	public String attendancePg(Model model) {

		Map<String, Object> resultMap = attendanceService.sltAttendanceList();
		model.addAttribute("attendanceList", resultMap.get("attendanceList"));
		model.addAttribute("attendanceToday", resultMap.get("attendanceToday")); // 출석여부
		model.addAttribute("isAttendance", StringUtil.isTodaySunday()); // 주일 여부
		model.addAttribute("currMonth", LocalDate.now(ZoneId.of("Asia/Seoul")).getMonthValue());

		// 경우의 수
		// 주일여부: Y/N
		// 출석여부: Y/N

		return "/app/psn/stu/page/atnd/atnd";
	}

	@MenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/atnd.ax")
	public ResponseEntity attendanceAx() {
		if (StringUtil.isTodaySunday()) {
			attendanceService.attendanceDo();
			return ResUtil.resSucc();
		} else {
			return ResUtil.resFail("주일만 출석 가능합니다.");
		}
	}

}