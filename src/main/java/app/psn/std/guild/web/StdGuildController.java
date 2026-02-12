package app.psn.std.guild.web;

import app.psn.std.guild.service.StdGildService;
import app.psn.std.home.service.StdHomeService;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;
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
@StdMenuInfo(navi = StdNaviEnum.STD_GILD, role = MberGrdEnum.STD)
public class StdGuildController {

	private final StdHomeService homeService;

	private final StdGildService stdGildService;

	/**
	 * 길드 정보
	 */
	@RequestMapping("/gild.pg")
	public String guildPg(Model model) {

		model.addAttribute("guildMberCnt", homeService.sltGuildMberList().size()); // 길드 숫자
		model.addAttribute("guildMberAccessList", homeService.sltGuildMberList()); // 길드접속자 목록
		model.addAttribute("guildInfo", homeService.sltGuildInfo()); // 길드정보

		return ViewPathEnum.STD.to("/gild/gild");

	}


	/**
	 * 길드 포스트 삭제
	 */
	@RequestMapping("/gild/delGildPost.ax")
	public ResponseEntity<BodyResVO<Object>> delGildPostAx(@RequestParam(value = "postSn", defaultValue = "") String postSn) {

		if ("".equals(postSn)) {
			ResUtil.resFail("필수값이 부족합니다! ❌");
		}

		stdGildService.delGildPost(Integer.parseInt(postSn));

		return ResUtil.resSucc("선택한 포스트가 삭제되었습니다! ✅");

	}

	/**
	 * 길드 포스트 추가
	 */
	@RequestMapping("/gild/regGildPost.ax")
	public ResponseEntity<BodyResVO<Object>> regGildPost(@RequestParam(value = "content", defaultValue = "") String content) {

		if ("".equals(content)) {
			ResUtil.resFail("필수값이 부족합니다! ❌");
		}

		stdGildService.regGildPost(StringUtil.xssSanitize(content));

		return ResUtil.resSucc("이야기가 공유되었습니다! 🚀");

	}

	/**
	 * 길드 포스트 목록
	 */
	@RequestMapping("/gild/getGildPost.ax")
	public ResponseEntity<BodyResVO<Object>> regGildPost() {

		return ResUtil.resSucc("포스트가 조회되었습니다! ✅", stdGildService.getTchGildPost());

	}

}