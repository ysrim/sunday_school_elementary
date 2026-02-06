package app.psn.std.intro.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.std.intro.mapper.IntroMapper;
import app.psn.std.intro.service.IntroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("introService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntroServiceImpl implements IntroService {

    private final IntroMapper introMapper;

    @Override
    public Integer stdMberCnt() {
        return introMapper.sltStdMberCnt();
    }

}