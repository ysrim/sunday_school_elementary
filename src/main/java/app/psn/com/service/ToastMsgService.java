package app.psn.com.service;

import app.psn.com.vo.ToastMsgVO;
import com.base.vo.ToastMsgEvent;

import java.util.List;

public interface ToastMsgService {

	/**
	 * 토스트 메시지 저장
	 */
	void insToastMsg(ToastMsgEvent toastMsgEvent);

	/**
	 * 토스트 매시지 목록
	 */
	List<ToastMsgVO> sltToastMsgList();

	/**
	 * 읽은 토스트 매시지 삭제
	 */
	void removeToast(Integer toastSn);

}