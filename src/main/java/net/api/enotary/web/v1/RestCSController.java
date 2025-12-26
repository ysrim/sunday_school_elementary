package net.api.enotary.web.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class RestCSController {

//	@Resource(name = "resService")
//	private ResService resService;

	@GetMapping("/test")
	@ResponseBody
	public String csDeploy() {
		return "test";
	}

	@GetMapping("/test2")
	@ResponseBody
	public String ancCsDeploy() {
		return "test2";
	}

}