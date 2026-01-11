package app.idx.reg.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.idx.reg.service.JoinService;
import app.idx.reg.vo.JoinMemberVO;
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

		String mberSn = joinDAO.sltMberSn();
		joinMemberVO.setMberSn(mberSn);

		// 1. MBER_INFO insert -> MBER_SN return;
		joinDAO.insMberInfo(joinMemberVO);

		// 2.AVATAR_INFO insert
		joinDAO.insAvatarInfo(joinMemberVO);

		// 3. 입력한 학년/반 정보로 GUILD_INFO 정보를 찾는다. -> GUILD_SN return;
		String guildSn = joinDAO.sltGuildInfo(joinMemberVO);
		log.info("guildSn: {}", guildSn);

		if (guildSn == null) {
			throw new RuntimeException("선택한 길드 정보를 가져올 수 없습니다");  // 롤백 처리됨
		}

		// 4.GUILD_MBER_LIST insert

		return true;

	}
}