package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.base.vo.ToastMsgEvent;

@Mapper
public interface ToastMsgMapper {

	void insToastMsg(ToastMsgEvent toastMsgEvent);

}