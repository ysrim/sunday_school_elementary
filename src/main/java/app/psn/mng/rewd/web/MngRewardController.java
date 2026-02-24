package app.psn.mng.rewd.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.com.service.RewardService;
import app.psn.com.vo.RewardVO;
import app.psn.mng.rewd.service.MngRewdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@MngMenuInfo(navi = MngNaviEnum.MNG_REWD, role = MberGrdEnum.MNG)
@RequestMapping("/mng")
public class MngRewardController {

	private final MngRewdService mngRewdService;

	private final RewardService rewardService;

	@RequestMapping("/rewd.pg")
	public String rewdPg() {

		return ViewPathEnum.MNG.to("/rewd/mngRewd");

	}

	@RequestMapping("/rewd/getStdList.ax")
	public ResponseEntity<ResponseBody<Object>> getStdListAx() {

		return ResUtil.resSucc(mngRewdService.getStdList());

	}

	@RequestMapping("/rewd/saveRewd.ax")
	public ResponseEntity<ResponseBody<Object>> saveRewdAx(@RequestBody @Valid RewardVO rewardVO) {

		rewardService.insMberReward(rewardVO);

		return ResUtil.resSucc();

	}

	@RequestMapping("/rewd/getRewdHistList.ax")
	public ResponseEntity<ResponseBody<Object>> getRewdHistListAx() {

		return ResUtil.resSucc(mngRewdService.getRewdHistList());

	}

}