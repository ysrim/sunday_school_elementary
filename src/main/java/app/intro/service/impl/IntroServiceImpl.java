package app.intro.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.api.enotary.service.ResService;

@Slf4j
@Service("introService")
public class IntroServiceImpl implements ResService {

	private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	@Resource(name = "introDAO")
	private IntroDAO introDAO;

	@PostConstruct
	public void serviceInit() {


	}

}