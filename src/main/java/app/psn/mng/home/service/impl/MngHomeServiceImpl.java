package app.psn.mng.home.service.impl;

import app.psn.mng.home.service.MngHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("mngHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngHomeServiceImpl implements MngHomeService {

}