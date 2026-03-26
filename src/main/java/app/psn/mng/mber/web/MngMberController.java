package app.psn.mng.mber.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.mng.mber.service.MngMberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
public class MngMberController {

	private final MngMberService mngMberService;

	@MngMenuInfo(navi = MngNaviEnum.MNG_MBER, role = MberGrdEnum.MNG)
	@RequestMapping("/mber.pg")
	public String mberPg() {

		return ViewPathEnum.MNG.to("/mber/mngMber");

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_MBER, role = MberGrdEnum.MNG)
	@RequestMapping(path = "/mber/resetPassword.ax")
	public ResponseEntity<ResponseBody<Object>> resetPasswordAx(@RequestParam(value = "mberSn", defaultValue = "0") String mberSn) {

		if ("0".equals(mberSn)) {
			return ResUtil.resSucc("필수정보가 없습니다.");
		}

		mngMberService.resetPasswordAx(Integer.parseInt(mberSn));

		return ResUtil.resSucc("패스워드가 a1234567* 초기화되었습니다.");

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_MBER, role = MberGrdEnum.MNG)
	@RequestMapping("/mber/getMberList.ax")
	public ResponseEntity<ResponseBody<Object>> getMberListAx() {

		return ResUtil.resSucc(mngMberService.getMberList());

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_ABSENT, role = MberGrdEnum.MNG)
	@RequestMapping("/absentee.pg")
	public String absenteePg() {

		return ViewPathEnum.MNG.to("/mber/mngAbsentee");

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_ABSENT, role = MberGrdEnum.MNG)
	@RequestMapping("/absentee/getAbsenteeList.ax")
	public ResponseEntity<ResponseBody<Object>> getAbsenteeListAx() {

		return ResUtil.resSucc(mngMberService.getMberList());

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_ATTEND, role = MberGrdEnum.MNG)
	@RequestMapping("/attendance.pg")
	public String attendancePg() {

		return ViewPathEnum.MNG.to("/mber/mngAttendance");

	}

	@MngMenuInfo(navi = MngNaviEnum.MNG_ATTEND, role = MberGrdEnum.MNG)
	@RequestMapping("/attendance/getWeeklyAttendList.ax")
	public ResponseEntity<ResponseBody<Object>> getWeeklyAttendListAx() {

		return ResUtil.resSucc(mngMberService.getMberList());

	}

}