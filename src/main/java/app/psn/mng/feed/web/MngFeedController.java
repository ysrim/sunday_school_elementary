package app.psn.mng.feed.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/mng/feed")
public class MngFeedController {

	private final MngFeedService mngFeedService;

	@RequestMapping("/feedList.pg")
	public String feedListPg() {

		return ViewPathEnum.MNG.to("/feed/mngFeedList");

	}

	@RequestMapping("/feedList.ax")
	public ResponseEntity<ResponseBody<Object>> feedListAx() {

		return ResUtil.resSucc(mngFeedService.sltFeedList());

	}

	@RequestMapping("/crtFeed.pg")
	public String crtFeedPg() {

		return ViewPathEnum.MNG.to("/feed/mngFeedCrt");

	}

	@RequestMapping("/crtFeedDo.ax")
	public ResponseEntity<ResponseBody<Object>> crtFeedDoAx(@RequestBody @Valid MngReqFeedVO mngReqFeedVO) {

		mngFeedService.crtFeedDo(mngReqFeedVO);

		return ResUtil.resSucc("글이 등록되었습니다!");

	}

	@RequestMapping("/udtFeed.pg")
	public String udtFeedPg(@Valid @RequestParam("postSn") Integer postSn, Model model) {

		model.addAttribute("feedVo", mngFeedService.sltFeed(postSn));

		return ViewPathEnum.MNG.to("/feed/mngFeedUdt");

	}

	@RequestMapping("/udtFeedDo.ax")
	public ResponseEntity<ResponseBody<Object>> udtFeedDoAx(@Valid @RequestBody MngReqFeedVO mngReqFeedVO) {

		mngFeedService.udtFeedDo(mngReqFeedVO);

		return ResUtil.resSucc("글이 수정되었습니다!");

	}

}