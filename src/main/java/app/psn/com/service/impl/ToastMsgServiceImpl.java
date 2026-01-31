package app.psn.com.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.vo.ToastMsgEvent;

import app.psn.com.mapper.AvatarMapper;
import app.psn.com.mapper.ToastMsgMapper;
import app.psn.com.service.AvatarService;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.service.ToastMsgService;
import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.RewardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("toastMsgService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class ToastMsgServiceImpl implements ToastMsgService {

	private final ToastMsgMapper toastMsgMapper;

	@Override
	public void insToastMsg(ToastMsgEvent toastMsgEvent) {
		toastMsgMapper.insToastMsg(toastMsgEvent);
	}
}