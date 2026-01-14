package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;

import app.idx.lgn.vo.LoginVO;
import app.idx.reg.service.JoinService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/std")
public class GuildController {

	@MenuInfo(navi = NaviEnum.STD_GILD, role = MberGrdEnum.STD)
	@RequestMapping("/guild.pg")
	public String guildPg(Model model) {
		return "/std/page/gulid";
	}

}