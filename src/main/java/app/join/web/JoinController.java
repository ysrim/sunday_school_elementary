package app.join.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.cmm.utl.ResUtil;
import com.base.cmm.vo.BodyResVO;

import app.join.service.JoinService;
import app.join.vo.JoinMemberVO;

@Controller
public class JoinController {

	@Resource(name = "joinService")
	private JoinService joinService;

	@RequestMapping(path = "/join.pg")
	public String joinPage(@ModelAttribute("joinFm") JoinMemberVO joinMemberVO) {
		return "/pages/app/join";
	}

	@RequestMapping(path = "/join.ax")
	@ResponseBody
	public BodyResVO joinAx(BodyResVO bodyResVO //
		, @Valid JoinMemberVO joinMemberVO //
		, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResUtil.resValid(bodyResVO, bindingResult);
		}

		return ResUtil.resSucc(bodyResVO, "성공");

	}

	@RequestMapping(path = "/idDupleChk.ax")
	@ResponseBody
	public BodyResVO idDupleChkAx(BodyResVO bodyResVO //
		, @RequestParam(name = "mberId", defaultValue = "") String mberId //
	) {

		if ("".equals(mberId)) {
			return ResUtil.resValid(bodyResVO, "아이디를 입력해주세요");
		}

		return joinService.idDupleChk(mberId) ? ResUtil.resSucc(bodyResVO, "성공") : ResUtil.resFail(bodyResVO, "실패");

	}

}