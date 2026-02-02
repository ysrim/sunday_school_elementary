package app.psn.com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.vo.ToastMsgEvent;

import app.psn.com.mapper.ToastMsgMapper;
import app.psn.com.service.ToastMsgService;
import app.psn.com.vo.ToastMsgVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("toastMsgService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToastMsgServiceImpl implements ToastMsgService {

	private final ToastMsgMapper toastMsgMapper;

	@Override
	public void insToastMsg(ToastMsgEvent toastMsgEvent) {
		toastMsgMapper.insToastMsg(toastMsgEvent);
	}

	@Override
	public List<ToastMsgVO> sltToastMsgList(int mberSn) {
		return toastMsgMapper.sltToastMsgList(mberSn);
	}

	@Override
	public void removeToast(int toastSn) {
		toastMsgMapper.removeToast(toastSn);
	}

}