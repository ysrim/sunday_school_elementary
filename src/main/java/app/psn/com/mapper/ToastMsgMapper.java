package app.psn.com.mapper;

import app.psn.com.vo.ToastMsgVO;
import com.base.vo.ToastMsgEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToastMsgMapper {

	void insToastMsg(ToastMsgEvent toastMsgEvent);

	List<ToastMsgVO> sltToastMsgList(Integer mberSn);

	void removeToast(Integer toastSn);

}