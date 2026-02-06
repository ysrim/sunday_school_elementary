package app.psn.std.join.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.std.join.mapper.StdJoinMapper;
import app.psn.std.join.service.StdJoinService;
import app.psn.std.join.vo.StdJoinMemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdJoinService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdJoinServiceImpl implements StdJoinService {

	private final StdJoinMapper stdJoinMapper;

	@Override
	public boolean idDupleChk(String memberId) {
		return stdJoinMapper.idDupleChk(memberId) == 0;
	}

	@Override
	@Transactional
	public void joinMber(StdJoinMemberVO stdJoinMemberVO) {

		// 1. 회원 번호(SN) 채번
		String mberSn = stdJoinMapper.sltMberSn();
		if (mberSn == null) {
			throw new RuntimeException("시스템 오류: 회원 번호를 생성할 수 없습니다.");
		}
		stdJoinMemberVO.setMberSn(mberSn);

		// 2. 기본 정보 및 아바타 정보 등록
		stdJoinMapper.insMberInfo(stdJoinMemberVO);
		stdJoinMapper.insAvatarInfo(stdJoinMemberVO);

		// 3. 길드 정보 조회
		String guildSn = stdJoinMapper.sltGuildInfo(stdJoinMemberVO);
		if (guildSn == null) {
			throw new RuntimeException("선택하신 학년/반에 해당하는 길드 정보가 없습니다.");
		}

		// 4. GUILD_MBER_LIST insert (누락된 로직 추가 필요)
		stdJoinMemberVO.setGuildSn(guildSn);
		stdJoinMapper.insGuildMberList(stdJoinMemberVO);

	}

}