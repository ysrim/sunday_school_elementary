package app.psn.std.home.web;

import app.psn.com.service.CacheService;
import app.psn.com.service.ToastMsgService;
import app.psn.std.home.service.StdHomeService;
import app.psn.std.qest.service.StdQestService;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.CommonUtil;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.vo.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
@StdMenuInfo(navi = StdNaviEnum.STD_HOME, role = MberGrdEnum.STD)
public class StdHomeController {

	private final CacheService cacheService;

	private final StdHomeService homeService;

	private final StdQestService stdQestService;

	private final ToastMsgService toastMsgService;

	/**
	 * 홈 대쉬보드 페이지
	 */
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		Integer mberSn = SessionUtil.getStdMberInfo().mberSn();

		// cache
		model.addAttribute("mberPoint", cacheService.sltPont(mberSn)); // 달란트
		model.addAttribute("mberLv", cacheService.sltLevel(mberSn)); // 레벨
		model.addAttribute("mberExp", cacheService.sltExp(mberSn)); // 경험치
		model.addAttribute("todayBibleVerse", cacheService.sltTodayBibleVerse()); // 오늘의 말씀

		// sql
		model.addAttribute("guildMberCnt", homeService.sltGildMberList().size()); // 길드맴버 숫자
		model.addAttribute("guildMberAccessList", homeService.sltGildMberAccessList()); // 길드접속자 목록
		model.addAttribute("guildInfo", homeService.sltGildInfo()); // 길드정보
		model.addAttribute("toastMsgList", toastMsgService.sltToastMsgList()); // 토스트 메시지
		model.addAttribute("qestCmpleteYn", stdQestService.qestCompleteChk(CommonUtil.setQuestPendingVO(4)) ? "Y" : "N"); // 맑씀읽기 퀘스트 여부

		// 미구현 기능: 공지사항
		return ViewPathEnum.STD.to("/home/stdHome");

	}

	/**
	 * 공지사항 상세보기
	 */
	@RequestMapping("/home/noticeCont.pg")
	public String noticeContPg() {

		// TODO 공지사항 컨텐츠 구현
		return ViewPathEnum.STD.to("/home/stdNoticeCont");

	}

	/**
	 * 토스트 메시지 삭제
	 */
	@RequestMapping("/home/removeToast.ax")
	public ResponseEntity<ResponseBody<Object>> removeToastAx(@RequestParam(name = "toastSn", defaultValue = "0") String toastSn) {

		if ("0".equals(toastSn)) {
			return ResUtil.resFail("변수 부족합니다! ❌");
		}

		toastMsgService.removeToast(Integer.parseInt(toastSn));

		return ResUtil.resSucc();

	}

	/**
	 * 오늘의 말씀 클릭이벤트
	 */
	@RequestMapping("/home/wordsAmen.ax")
	public ResponseEntity<ResponseBody<Object>> wordsAmenAx() {

		return homeService.wordsAmenDo() ? ResUtil.resSucc() : ResUtil.resFail("이미 수행했습니다! ❌");

	}

}