to-do
1. 페이지 리다이렉트 할경우 alert 메시지 보이게 > 쿠키에 데이터 담아서 보냄
2. 페이지 얼럿 html 정리
3. 인터셉터에 코드 정보 셋


<dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
</dependency>

// 저장 시 암호화
String encryptedPwd = BCrypt.hashpw(joinMemberVO.getPwd(), BCrypt.gensalt());
joinMemberVO.setPwd(encryptedPwd);
joinMapper.insMberInfo(joinMemberVO);

// 비교 시 matches 사용
if (!BCrypt.checkpw(loginVO.getPwd(), sessionVO.getPwd())) {
    throw new RuntimeException("패스워드가 틀렸습니다.");
}
