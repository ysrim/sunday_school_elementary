package app.psn.com.service.impl;

import app.psn.com.mapper.ToastMsgMapper;
import app.psn.com.service.ToastMsgService;
import app.psn.com.vo.ToastMsgVO;
import com.base.utl.SessionUtil;
import com.base.vo.ToastMsgEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public List<ToastMsgVO> sltToastMsgList() {

		return toastMsgMapper.sltToastMsgList(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public void removeToast(Integer toastSn) {

		toastMsgMapper.removeToast(toastSn);

	}

}