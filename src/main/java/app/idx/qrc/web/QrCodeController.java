package app.idx.qrc.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.utl.ResUtil;

import app.idx.qrc.service.QrCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/idx")
public class QrCodeController {

	private final QrCodeService qrCodeService;

	@RequestMapping(path = "/qrCode/stdMember.pg")
	public String stdMemberAx(@RequestParam(name = "value", defaultValue = "") String value, Model model) {
		log.info("valeu=> {}", value);
		model.addAttribute("qrInfo", "".equals(value) ? null : qrCodeService.sltMberSn(value));
		return "/app/idx/qr";
	}

}