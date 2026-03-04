package app.psn.mng.feed.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.mng.feed.service.MngFeedService;
import app.psn.mng.feed.vo.MngReqFeedVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@MngMenuInfo(navi = MngNaviEnum.MNG_FEED, role = MberGrdEnum.MNG)
@RequestMapping("/mng")
public class MngFeedController {

	private final MngFeedService mngFeedService;

	@RequestMapping("/feed.pg")
	public String feedPg(Model model) {

		model.addAttribute("feedList", mngFeedService.sltFeedList());

		return ViewPathEnum.MNG.to("/feed/mngFeed");

	}

	@RequestMapping("/feed/crtFeed.pg")
	public String crtFeedPg() {

		return ViewPathEnum.MNG.to("/feed/mngFeedCont");

	}

	@RequestMapping("/feed/crtFeedDo.ax")
	public ResponseEntity<ResponseBody<Object>> crtFeedDoAx(@RequestBody @Valid MngReqFeedVO mngReqFeedVO) {

		mngFeedService.crtFeedDo(mngReqFeedVO);

		return ResUtil.resSucc("글이 등록되었습니다!");

	}

	@RequestMapping("/feed/udtFeed.pg")
	public String udtFeedPg(@Valid Integer postSn, Model model) {

		model.addAttribute("feedVo", mngFeedService.sltFeed(postSn));

		return ViewPathEnum.MNG.to("/feed/mngFeedCont");

	}

	@RequestMapping("/feed/udtFeedDo.ax")
	public ResponseEntity<ResponseBody<Object>> udtFeedDoAx(@RequestBody @Valid MngReqFeedVO mngReqFeedVO) {

		mngFeedService.udtFeedDo(mngReqFeedVO);

		return ResUtil.resSucc("글이 수정되었습니다!");

	}

}