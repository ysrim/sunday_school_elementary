package app.psn.mng.rewd.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.mng.rewd.service.MngRewdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@MngMenuInfo(navi = MngNaviEnum.MNG_REWD, role = MberGrdEnum.MNG)
@RequestMapping("/mng")
public class MngRewardController {

	private final MngRewdService mngRewdService;

	@RequestMapping("/rewd.pg")
	public String rewdPg() {

		return ViewPathEnum.MNG.to("/rewd/mngRewd");

	}

	@RequestMapping("/rewd/getStdList.ax")
	public ResponseEntity<ResponseBody<Object>> getStdListAx() {

		return ResUtil.resSucc(mngRewdService.getStdList());

	}

}