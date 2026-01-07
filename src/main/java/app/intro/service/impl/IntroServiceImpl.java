package app.intro.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.intro.service.IntroService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("introService")
public class IntroServiceImpl implements IntroService {

	private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	@Resource(name = "introDAO")
	private IntroDAO introDAO;

	@PostConstruct
	public void serviceInit() {

	}

}