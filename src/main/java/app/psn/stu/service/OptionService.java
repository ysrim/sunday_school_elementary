package app.psn.stu.service;

import java.util.HashMap;
import java.util.List;

import app.psn.stu.vo.PointHistoryVO;

public interface OptionService {

	String QrCodeStr(); // QR코드 정보 문자열

	boolean pwChg(String currentPw, String newPw);

	List<PointHistoryVO> sltPointHistory();

	HashMap<String, Integer> sltWeekStatics();

}