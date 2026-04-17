package app.psn.std.join.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.std.join.service.StdJoinService;
import app.psn.std.join.vo.StdJoinMemberVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdJoinController {

	private final StdJoinService stdJoinService;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입 페이지
	 */
	@RequestMapping(path = "/join.pg")
	public String joinPage(@ModelAttribute("joinFm") StdJoinMemberVO stdJoinMemberVO) {

		return ViewPathEnum.STD.to("/idx/join");

	}

	/**
	 * 회원 가입 요청
	 */
	@RequestMapping(path = "/join.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> joinAx(@Valid StdJoinMemberVO stdJoinMemberVO) {

		stdJoinMemberVO.setPwd(passwordEncoder.encode(stdJoinMemberVO.getPwd()));

		stdJoinService.joinMber(stdJoinMemberVO);

		return ResUtil.resSucc("가입을 축하합니다! " + stdJoinMemberVO.getNcnm() + " 용사님! ✅");

	}

	@RequestMapping(path = "/test.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> testAx() {

		List<String> tmpList = new ArrayList<>();
		String a = "박태람,남,4,1,ptr9201,xofka9201!,박태람,믿음의 전사";
		tmpList.add(a);

		for (String s : tmpList) {
			String[] tmpAry = s.split(",");
			StdJoinMemberVO vo = new StdJoinMemberVO();
			vo.setMberNm(tmpAry[0]); // 이름
			vo.setSexdstn("남".equals(tmpAry[1]) ? "M" : "F"); // 성별
			vo.setGrade(tmpAry[2]); // 학년
			vo.setCls(tmpAry[3]); // 반
			vo.setMberId(tmpAry[4]); //아이디
			vo.setPwd(tmpAry[5]); // 비번
			vo.setNcnm(tmpAry[6]); // 캐릭터명
			vo.setOccpCode(getAvatarId(tmpAry[7])); // 캐릭터 선택
			vo.setPwd(passwordEncoder.encode(vo.getPwd()));
			stdJoinService.joinMber(vo);
		}

		return ResUtil.resSucc("가입을 축하합니다!");

	}

	public String getAvatarId(String avatarName) {
		return switch (avatarName) {
			case "믿음의 전사" -> "001";
			case "절제의 수호자" -> "002";
			case "소망의 성기사" -> "003";
			case "평안의 궁수" -> "004";
			case "지혜의 마법사" -> "005";
			case "사랑의 힐러" -> "006";
			case "성장의 드루이드" -> "007";
			case "기쁨의 음유시인" -> "008";
			default -> "000"; // 일치하는 캐릭터가 없을 경우 기본값
		};
	}

	/**
	 * 아이디 중복 체크 요청
	 */
	@RequestMapping(path = "/idDupleChk.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> idDupleChkAx(@RequestParam(name = "mberId", defaultValue = "") String mberId) {

		if ("".equals(mberId)) {
			return ResUtil.resValid("잘못된 형식의 아이디입니다! ❌");
		}

		return stdJoinService.idDupleChk(mberId) ? ResUtil.resSucc("사용 가능한 아이디입니다! ✅") : ResUtil.resFail("이미 사용 중인 아이디입니다! ❌");

	}

}