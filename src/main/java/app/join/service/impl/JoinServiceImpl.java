package app.join.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.join.service.JoinService;
import app.login.service.LoginService;
import app.login.service.impl.LoginDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("joinService")
public class JoinServiceImpl implements JoinService {

	private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	@Resource(name = "joinDAO")
	private JoinDAO joinDAO;

	@PostConstruct
	public void serviceInit() {
		// do something
	}

	@Override
	public boolean idDupleChk(String memberId) {
		System.out.println("joinDAO.idDupleChk(memberId)> " + joinDAO.idDupleChk(memberId));
		return joinDAO.idDupleChk(memberId) > 0 ? false : true;
	}
}