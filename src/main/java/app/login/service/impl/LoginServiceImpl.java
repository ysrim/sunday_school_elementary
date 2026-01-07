package app.login.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.login.service.LoginService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	@Resource(name = "loginDAO")
	private LoginDAO loginDAO;

	@PostConstruct
	public void serviceInit() {

		// do something

	}

}