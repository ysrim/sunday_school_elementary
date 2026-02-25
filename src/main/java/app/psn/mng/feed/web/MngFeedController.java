package app.psn.mng.feed.web;

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
@MngMenuInfo(navi = MngNaviEnum.MNG_FEED, role = MberGrdEnum.MNG)
@RequestMapping("/mng")
public class MngFeedController {

	@RequestMapping("/feed.pg")
	public String feedPg() {

		return ViewPathEnum.MNG.to("/feed/mngFeed");

	}

	@RequestMapping("/feed/cont.pg")
	public String contPg() {

		return ViewPathEnum.MNG.to("/feed/mngFeedCont");

	}

}