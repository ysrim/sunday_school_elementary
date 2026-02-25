package app.psn.mng.mber.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@MngMenuInfo(navi = MngNaviEnum.MNG_MBER, role = MberGrdEnum.MNG)
@RequestMapping("/mng")
public class MngMberController {

	private final MngMberService mngMberService;

	@RequestMapping("/mber.pg")
	public String mberPg() {

		return ViewPathEnum.MNG.to("/mber/mngMber");

	}

	@RequestMapping("/mber/getMberList.ax")
	public ResponseEntity<ResponseBody<Object>> getMberListAx() {

		return ResUtil.resSucc(mngMberService.getMberList());

	}

}