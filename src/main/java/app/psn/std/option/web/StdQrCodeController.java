package app.psn.std.option.web;

import app.psn.std.option.service.StdQrCodeService;
import com.base.enumm.com.ViewPathEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdQrCodeController {

	private final StdQrCodeService stdQrCodeService;

	/**
	 * QR코드 사용자 정보
	 */
	@RequestMapping(path = "/qrCode/stdMember.pg")
	public String stdMemberAx(@RequestParam(name = "value", defaultValue = "") String value, Model model) {

		model.addAttribute("qrInfo", "".equals(value) ? null : stdQrCodeService.sltMberSn(value));

		return ViewPathEnum.STD.to("/idx/qr");

	}

}