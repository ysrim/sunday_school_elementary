package app.psn.stu.service;

import app.psn.stu.vo.PointHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionService {

    String QrCodeStr(); // QR코드 정보 문자열

    boolean pwChg(String currentPw, String newPw);

    List<PointHistoryVO> sltPointHistory();

}