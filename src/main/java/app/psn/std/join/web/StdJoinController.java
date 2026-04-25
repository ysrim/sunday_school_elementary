package app.psn.std.join.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.std.join.service.StdJoinService;
import app.psn.std.join.vo.StdJoinMemberVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdJoinController {

	private final StdJoinService stdJoinService;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입 페이지
	 */
	@RequestMapping(path = "/join.pg")
	public String joinPage(@ModelAttribute("joinFm") StdJoinMemberVO stdJoinMemberVO) {

		return ViewPathEnum.STD.to("/idx/join");

	}

	/**
	 * 회원 가입 요청
	 */
	@RequestMapping(path = "/join.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> joinAx(@Valid StdJoinMemberVO stdJoinMemberVO) {

		stdJoinMemberVO.setPwd(passwordEncoder.encode(stdJoinMemberVO.getPwd()));

		stdJoinService.joinMber(stdJoinMemberVO);

		return ResUtil.resSucc("가입을 축하합니다! " + stdJoinMemberVO.getNcnm() + " 용사님! ✅");

	}

	@RequestMapping(path = "/test.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> testAx() {

		List<String> tmpList = new ArrayList<>();
		tmpList.add("김예솔,여,4,3,sol0403,sol0403@,김예솔,성장의 드루이드");
		tmpList.add("안민준,남,4,1,민준4101,love4101!,안민준,믿음의 전사");
		tmpList.add("우시우,남,4,1,시우4102,love4102!,우시우,믿음의 전사");
		tmpList.add("이노엘,남,4,1,노엘4103,love4103!,이노엘,믿음의 전사");
		tmpList.add("최연호,남,4,1,연호4104,love4104!,최연호,믿음의 전사");
		tmpList.add("한윤성,남,4,1,윤성4105,love4105!,한윤성,믿음의 전사");
		tmpList.add("김한솔,남,4,5,한솔7120,gksthf7120!,김한솔,믿음의 전사");
		tmpList.add("김다솜,여,4,2,dasom42,dasom0402@,김다솜,소망의 성기사");
		tmpList.add("노서율,여,4,2,seoyul42,seoyul0402@,노서율,평안의 궁수");
		tmpList.add("이리나,여,4,2,rina42,rina0402@,이리나,지혜의 마법사");
		tmpList.add("임소은,여,4,2,soeun42,soeun0402@,임소은,사랑의 힐러");
		tmpList.add("최연아,여,4,2,yeona42,yeona0402@,최연아,성장의 드루이드");
		tmpList.add("하은비,여,4,2,eunbi42,eunbi0402@,하은비,기쁨의 음유시인");
		tmpList.add("김흥구,남,5,3,andrew5425,andrew5425!@,김흥구,믿음의 전사");
		tmpList.add("서경찬,남,4,3,chan0403,chan0403@,서경찬,믿음의 전사");
		tmpList.add("송하민,남,4,3,hamin0403,hamin0403@,송하민,절제의 수호자");
		tmpList.add("이서준,남,4,3,seojun0403,seojun0403@,이서준,소망의 성기사");
		tmpList.add("이이삭,남,4,3,isak0403,isak0403@,이이삭,평안의 궁수");
		tmpList.add("이지안,남,4,3,jian0403,jian0403@,이지안,사랑의 힐러");
		tmpList.add("최준우,남,4,3,junu0403,junu0403@,최준우,지혜의 마법사");
		tmpList.add("문애진,여,5,4,ajmoon,ajmoon724724@,문애진,사랑의 힐러");
		tmpList.add("이지온,여,4,4,jion3927,jion3927@@,이지온,믿음의 전사");
		tmpList.add("이다나,여,4,4,dana3927,dana3927@@,이다나,평안의 궁수");
		tmpList.add("조예빈,여,4,4,yebin3927,yebin3927@@,조예빈,소망의 성기사");
		tmpList.add("홍세빈,여,4,4,sebin3927,sebin3927@@,홍세빈,사랑의 힐러");
		tmpList.add("박서연,여,4,4,syeon3927,syeon3927@@,박서연,성장의 드루이드");
		tmpList.add("오다빈,여,4,4,dabin3927,dabin3927@@,오다빈,지혜의 마법사");
		tmpList.add("문영신,여,4,2,sini42,sini0402@,문영신,믿음의 전사");
		tmpList.add("안서준,남,4,5,서준0269,서준0269@,안서준,믿음의 전사");
		tmpList.add("제은성,남,4,5,은성59,dmstjd5959@,제은성,믿음의 전사");
		tmpList.add("송윤우,남,4,5,윤우4740,dbsdn4740@,송윤우,믿음의 전사");
		tmpList.add("임정훈,남,4,5,정훈5146,wjdgns5146@,임정훈,믿음의 전사");
		tmpList.add("문상권,남,4,5,상권0401,tkdrnjs0401@,문상권,믿음의 전사");
		tmpList.add("우도준,남,4,5,도준1497,ehwns1497@,우도준,믿음의 전사");
		tmpList.add("박명제,여,5,2,명제3570,abc3570!,박명제,성장의 드루이드");
		tmpList.add("양에스더,여,4,6,esther0523,esther0406@,양에스더,절제의 수호자");
		tmpList.add("성채윤,여,4,6, yoon0105, yoon0406@,성채윤,지혜의 마법사");
		tmpList.add("윤정아,여,4,6,jeong0402,jeong0406@,윤정아,사랑의 힐러");
		tmpList.add("김라희,여,4,6,hee0501,hee0406@,김라희,소망의 성기사");
		tmpList.add("박민숙,여,6,5,Qr273900,Qr273900$,박민숙,믿음의 전사");
		tmpList.add("김우빈,남,5,1,class12,bongdong1!,김우빈,믿음의 전사");
		tmpList.add("김하민,남,5,1,class13,bongdong1!,김하민,믿음의 전사");
		tmpList.add("목준수,남,5,1,class14,bongdong1!,목준수,믿음의 전사");
		tmpList.add("정재후,남,5,1,class15,bongdong1!,정재후,믿음의 전사");
		tmpList.add("박연희,여,6,4,연희3570,aaa3570!,박연희,성장의 드루이드");
		tmpList.add("이세령,여,5,2,세령3559,abc3559!,이세령,사랑의 힐러");
		tmpList.add("신지안,여,5,2,지안9349,abc9349!,신지안,지혜의 마법사");
		tmpList.add("이가을,여,5,2,가을5458,abc5458!,이가을,절제의 수호자");
		tmpList.add("진다은,여,5,2,다은7223,abc7223!,진다은,기쁨의 음유시인");
		tmpList.add("서은솔,여,5,2,은솔2491,abc2491!,서은솔,사랑의 힐러");
		tmpList.add("박주아,여,5,2,주아0095,abc0095!,박주아,사랑의 힐러");
		tmpList.add("임하은,여,5,2,하은3428,abc3428!,임하은,평안의 궁수");
		tmpList.add("박태람,남,4,1,ptr9201,xofka9201!,박태람,믿음의 전사");
		tmpList.add("안은찬,남,5,3,은찬01,eunchan0979!@,안은찬,절제의 수호자");
		tmpList.add("이주혁,남,5,3,주혁02,juhyuk7777@#,이주혁,소망의 성기사");
		tmpList.add("이예준,남,5,3,yj7579,yj7579!!,이예준,지혜의 마법사");
		tmpList.add("이강산,남,5,3,lm0904,lm0904#$,이강산,사랑의 힐러");
		tmpList.add("이희율,남,5,3,hl0979,hl0979!@,이희율,성장의 드루이드");
		tmpList.add("김도윤,남,5,3,dh2606,dh2606$%,김도윤,기쁨의 음유시인");
		tmpList.add("안동섭,남,6,1,jesus7509,lesuok7309!,안동섭,믿음의 전사");
		tmpList.add("김다연,여,5,4,dayeon,dayeon12111211@,김다연,사랑의 힐러");
		tmpList.add("이재은,여,5,4,jaeeun,jaeeun820820@,이재은,사랑의 힐러");
		tmpList.add("이아연,여,5,4,ayeon,ayeon109109@,이아연,사랑의 힐러");
		tmpList.add("이라엘,여,5,4,rael,rael513513@,이라엘,사랑의 힐러");
		tmpList.add("임하랑,여,5,4,harang,harang721721@,임하랑,사랑의 힐러");
		tmpList.add("김슬아,여,5,4,seula,seula804804@,김슬아,사랑의 힐러");
		tmpList.add("강유연,여,5,4,yuyeon,yuyeon416416@,강유연,사랑의 힐러");
		tmpList.add("안주희,여,4,4,77hosanna,saint0303@@,안주희,기쁨의 음유시인");
		tmpList.add("김시현,남,6,1,seehyun,seehyun01!!,김시현,믿음의 전사");
		tmpList.add("김희원,남,6,1,heewon,heewon02!!,김희원,믿음의 전사");
		tmpList.add("문찬율,남,6,1,chanyoul,chanyoul03!!,문찬율,믿음의 전사");
		tmpList.add("박예현,남,6,1,yeahyun,yeahyun04!!,박예현,믿음의 전사");
		tmpList.add("유은결,남,6,1,eungyeol,eungyeol05!!,유은결,믿음의 전사");
		tmpList.add("유원욱,남,6,1,wonyouk,wonyouk06!!,유원욱,믿음의 전사");
		tmpList.add("이경숙,여,4,6,suk0728, suk0406@,이경숙,믿음의 전사");
		tmpList.add("이하은,여,6,2,haeun,ha1230,이하은,사랑의 힐러");
		tmpList.add("하단비,여,6,2,dan1230,dan1230,하단비,절제의 수호자");
		tmpList.add("윤아윤,여,6,2,yuon1230,yuon1230,윤아윤,지혜의 마법사");
		tmpList.add("곽서영,여,6,2,yong1230,yong1230,곽서영,평안의 궁수");
		tmpList.add("서은비,여,6,2,bi1230,bi1230,서은비,믿음의 전사");
		tmpList.add("이동수,남,5,1,class11,bongdong1!,이동수,믿음의 전사");
		tmpList.add("송하준,남,6,3,하준1825,gkwns1825@@,송하준,믿음의 전사");
		tmpList.add("이승유,남,6,3,승유7457,tmddb7457@@,이승유,기쁨의 음유시인");
		tmpList.add("함규진,남,6,3,규진5452,rbwls5452@@,함규진,소망의 성기사");
		tmpList.add("임성찬,남,6,3,성찬1316,tjdcks1316@@,임성찬,지혜의 마법사");
		tmpList.add("서은찬,남,6,3,은찬5489,dmscks5489@@,서은찬,성장의 드루이드");
		tmpList.add("이보람,여,6,3,뽀담뽀담8572,Qheka8572@@,이보람,사랑의 힐러");
		tmpList.add("고예은,여,6,4,예은1410,aaa1410!,고예은,사랑의 힐러");
		tmpList.add("이다예,여,6,4,다예0550,aaa0550!,이다예,지혜의 마법사");
		tmpList.add("성예은,여,6,4,예은0643,aaa0643!,성예은,절제의 수호자");
		tmpList.add("김가림,여,6,4,가림1441,aaa1441!,김가림,기쁨의 음유시인");
		tmpList.add("한태주,여,6,4,태주0180,aaa0180!,한태주,사랑의 힐러");
		tmpList.add("정혜경,여,6,6,혜경0601,gPrud0606@,정혜경,믿음의 전사");
		tmpList.add("우시형,남,6,5,sihyung9001,si90011@,우시형,절제의 수호자");
		tmpList.add("김민준,남,6,5,minjun9815,min98155@,김민준,지혜의 마법사");
		tmpList.add("김하준,남,6,5,hajun9061,ha90611@,김하준,사랑의 힐러");
		tmpList.add("이의준,남,6,5,euijun2073,eui20733@,이의준,소망의 성기사");
		tmpList.add("김은찬,남,6,5,eunchan1019,eun10199@,김은찬,평안의 궁수");
		tmpList.add("이도윤,남,6,5,doyoon2661,do26611@,이도윤,기쁨의 음유시인");
		tmpList.add("하누리,남,6,2,ha1230,ha1240,하누리,소망의 성기사");
		tmpList.add("이온유,여,6,6,온유0127,dhsdb0606@,이온유,절제의 수호자");
		tmpList.add("이하엘,여,6,6,하엘0226,gkdpf0606@,이하엘,소망의 성기사");
		tmpList.add("양시온,여,6,6,시온1013,tldhs0606@,양시온,평안의 궁수");
		tmpList.add("이주은,여,6,6,주은0807,wndms0606@,이주은,지혜의 마법사");
		tmpList.add("이채현,여,6,6,채현0404,cpgis0606@,이채현,사랑의 힐러");
		tmpList.add("김나연,여,6,6,나연0101,skdus0606@,김나연,성장의 드루이드");
		tmpList.add("최수빈,여,6,6,수빈0218,tnqls0606@,최수빈,기쁨의 음유시인");

		for (String s : tmpList) {
			String[] tmpAry = s.split(",");
			StdJoinMemberVO vo = new StdJoinMemberVO();
			vo.setMberNm(tmpAry[0].trim()); // 이름
			vo.setSexdstn("남".equals(tmpAry[1]) ? "M" : "F"); // 성별
			vo.setGrade(tmpAry[2].trim()); // 학년
			vo.setCls(tmpAry[3].trim()); // 반
			vo.setMberId(tmpAry[4].trim()); //아이디
			vo.setPwd(tmpAry[5].trim()); // 비번
			vo.setNcnm(tmpAry[6].trim()); // 캐릭터명
			vo.setOccpCode(getAvatarId(tmpAry[7].trim())); // 캐릭터 선택
			vo.setPwd(passwordEncoder.encode(vo.getPwd()));
			//stdJoinService.joinMber(vo);
		}

		return ResUtil.resSucc("가입을 축하합니다!");

	}

	public String getAvatarId(String avatarName) {
		return switch (avatarName) {
			case "믿음의 전사" -> "001";
			case "절제의 수호자" -> "002";
			case "소망의 성기사" -> "003";
			case "평안의 궁수" -> "004";
			case "지혜의 마법사" -> "005";
			case "사랑의 힐러" -> "006";
			case "성장의 드루이드" -> "007";
			case "기쁨의 음유시인" -> "008";
			default -> "000"; // 일치하는 캐릭터가 없을 경우 기본값
		};
	}

	/**
	 * 아이디 중복 체크 요청
	 */
	@RequestMapping(path = "/idDupleChk.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> idDupleChkAx(@RequestParam(name = "mberId", defaultValue = "") String mberId) {

		if ("".equals(mberId)) {
			return ResUtil.resValid("잘못된 형식의 아이디입니다! ❌");
		}

		return stdJoinService.idDupleChk(mberId) ? ResUtil.resSucc("사용 가능한 아이디입니다! ✅") : ResUtil.resFail("이미 사용 중인 아이디입니다! ❌");

	}

}