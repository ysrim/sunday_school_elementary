package net.api.enotary.web.v1;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.cmm.utl.ResUtil;
import com.base.cmm.vo.BodyResVO;

import net.api.enotary.vo.ReqDocCrtVO;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RestNotaryController {

	//	@Resource(name = "resService")
	//	private ResService resService;

	/**
	 * @param bodyResVO
	 * @param reqDocCrtVO
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/reqDoc/crt")
	public BodyResVO reqDocCrt(BodyResVO bodyResVO //
		, @RequestBody @Valid ReqDocCrtVO reqDocCrtVO //
		, BindingResult bindingResult //
	) {

		if (bindingResult.hasErrors()) {
			return ResUtil.resValid(bodyResVO, bindingResult);
		}
		bodyResVO.setRtnCd("001");
		bodyResVO.setRtnMsg("test");
		return bodyResVO;

	}

}