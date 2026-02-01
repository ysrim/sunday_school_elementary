package app.psn.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.vo.ToastMsgEvent;

import app.psn.com.vo.ToastMsgVO;

@Mapper
public interface ToastMsgMapper {

	void insToastMsg(ToastMsgEvent toastMsgEvent);

	List<ToastMsgVO> sltToastMsgList(int mberSn);

	void removeToast(int toastSn);

}