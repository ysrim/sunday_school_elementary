package app.psn.std.join.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.std.join.mapper.JoinMapper;
import app.psn.std.join.service.JoinService;
import app.psn.std.join.vo.JoinMemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("joinService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinServiceImpl implements JoinService {

    private final JoinMapper joinMapper;

    @Override
    public boolean idDupleChk(String memberId) {
        return joinMapper.idDupleChk(memberId) == 0;
    }

    @Override
    @Transactional
    public boolean joinMber(JoinMemberVO joinMemberVO) {

        // 1. 회원 번호(SN) 채번
        String mberSn = joinMapper.sltMberSn();
        if (mberSn == null) {
            throw new RuntimeException("시스템 오류: 회원 번호를 생성할 수 없습니다.");
        }
        joinMemberVO.setMberSn(mberSn);

        // 2. 기본 정보 및 아바타 정보 등록
        joinMapper.insMberInfo(joinMemberVO);
        joinMapper.insAvatarInfo(joinMemberVO);

        // 3. 길드 정보 조회
        String guildSn = joinMapper.sltGuildInfo(joinMemberVO);
        if (guildSn == null) {
            throw new RuntimeException("선택하신 학년/반에 해당하는 길드 정보가 없습니다.");
        }

        // 4. GUILD_MBER_LIST insert (누락된 로직 추가 필요)
        joinMemberVO.setGuildSn(guildSn);
        joinMapper.insGuildMberList(joinMemberVO);

        return true;

    }

}