package app.psn.mng.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
@MngMenuInfo(navi = MngNaviEnum.MNG_HOME, role = MberGrdEnum.MNG)
public class MngHomeController {

	/**
	 * 홈 대쉬보드 페이지
	 */
	@RequestMapping("/home.pg")
	public String homePg() {

		return ViewPathEnum.MNG.to("/home/mngHome");

	}

}