package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;

import app.idx.lgn.vo.LoginVO;
import app.idx.reg.service.JoinService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/std")
public class AttendanceController {

	@Resource(name = "joinService")
	private JoinService joinService;

	@MenuInfo(navi = NaviEnum.STD_ATND, role = MberGrdEnum.STD)
	@RequestMapping("/attendance.pg")
	public String attendancePg(Model model) {
		return "/std/page/home";
	}

	@MenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/attendance.ax")
	public ResponseEntity attendanceAx(@RequestBody @Valid LoginVO loginVO) {

		log.debug("loginVO: {}", loginVO);

		return ResUtil.resSucc("TEST");

	}

}