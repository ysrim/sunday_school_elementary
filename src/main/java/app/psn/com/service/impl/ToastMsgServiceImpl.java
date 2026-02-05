package app.psn.com.service.impl;

import java.util.List;

import com.base.utl.SessionUtil;
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
    public List<ToastMsgVO> sltToastMsgList() {
        return toastMsgMapper.sltToastMsgList(SessionUtil.getMberInfo().getMberSn());
    }

    @Override
    public void removeToast(Integer toastSn) {
        toastMsgMapper.removeToast(toastSn);
    }

}