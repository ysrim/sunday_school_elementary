package app.psn.stu.service;

public interface OptionService {

	String QrCodeStr(); // QR코드 정보 문자열

	boolean pwChg(String currentPw, String newPw);

}