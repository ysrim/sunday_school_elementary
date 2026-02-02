package app.idx.reg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.idx.reg.mapper.JoinMapper;
import app.idx.reg.service.JoinService;
import app.idx.reg.vo.JoinMemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("joinService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinServiceImpl implements JoinService {

	// @Value("#{globalsProps['Service.year']}")
	// private String srvYear;

	private final JoinMapper joinMapper;

	@Override
	public boolean idDupleChk(String memberId) {
		// 3. 삼항 연산자 제거: 카운트가 0이면 사용 가능(true)
		return joinMapper.idDupleChk(memberId) == 0;
	}

	@Override
	// 4. 쓰기가 일어나는 곳만 @Transactional 별도 지정 (rollbackFor 생략 시 기본 RuntimeException 포함)
	@Transactional
	public boolean joinMber(JoinMemberVO joinMemberVO) {

		// 1. 회원 번호(SN) 채번
		String mberSn = joinMapper.sltMberSn();
		if (mberSn == null) {
			// 예외 발생 시 자동 롤백됩니다.
			throw new RuntimeException("시스템 오류: 회원 번호를 생성할 수 없습니다.");
		}
		joinMemberVO.setMberSn(mberSn);

		// 2. 기본 정보 및 아바타 정보 등록
		joinMapper.insMberInfo(joinMemberVO);
		joinMapper.insAvatarInfo(joinMemberVO);

		// 3. 길드 정보 조회
		String guildSn = joinMapper.sltGuildInfo(joinMemberVO);
		log.info("Check Guild SN: {}", guildSn); // 로그 메시지 명확화

		if (guildSn == null) {
			// 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("선택하신 학년/반에 해당하는 길드 정보가 없습니다.");
		}

		// 4. GUILD_MBER_LIST insert (누락된 로직 추가 필요)
		joinMemberVO.setGuildSn(guildSn);
		joinMapper.insGuildMberList(joinMemberVO);

		return true;

	}
}