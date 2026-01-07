package app.join.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.join.service.JoinService;
import app.join.vo.JoinMemberVO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("joinService")
public class JoinServiceImpl implements JoinService {

	private @Value("#{globalsProps['Service.year']}") String srvYear;

	@Resource(name = "joinDAO")
	private JoinDAO joinDAO;

	@PostConstruct
	public void serviceInit() {
		// do something
	}

	@Override
	public boolean idDupleChk(String memberId) {
		return joinDAO.idDupleChk(memberId) > 0 ? false : true;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class})
	public boolean joinMber(JoinMemberVO joinMemberVO) {

		// 1. MBER_INFO insert -> MBER_SN return;
		joinDAO.insMberInfo(joinMemberVO);

		// 2.AVATAR_INFO insert
		joinDAO.insAvatarInfo(joinMemberVO);

		// 3. 입력한 학년/반 정보로 GUILD_INFO 정보를 찾는다. -> GUILD_SN return;
		String guildSn = joinDAO.sltGuildInfo(joinMemberVO);
		log.info("guildSn: {}", guildSn);

		if (guildSn == null) {
			throw new RuntimeException("이름이 null일 수 없습니다.");  // 롤백 처리됨
		}

		// 4.GUILD_MBER_LIST insert

		throw new NullPointerException("Throw Force Exception");

	}
}