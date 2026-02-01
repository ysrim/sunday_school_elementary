package app.psn.com.service;

import java.util.List;

import com.base.vo.ToastMsgEvent;

import app.psn.com.vo.ToastMsgVO;

public interface ToastMsgService {

	void insToastMsg(ToastMsgEvent toastMsgEvent);

	List<ToastMsgVO> sltToastMsgList(int mberSn);

	void removeToast(int toastSn);

}