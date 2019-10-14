package kr.co.redbull.user.service.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.ExcelWriter;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.user.service.Level;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExcelWriter excelWriter;
	
	@Autowired
	private MailSender mailSender;

	// Dao와 연결
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	// level upgrade: 레벨과 포인트에 따라 분기하여 업그레이드
	// 1. 전체 사용자를 조회
	// 2. 대상자를 선별
		// 2.1 BASIC 사용자가 포인트 500점 이상이면 : BASIC -> SILVER
		// 2.2 SILVER 사용자가 포인트 1000점 이상이면 : SILVER -> GOLD
		// 2.3 GOLD : 대상 아님
	// 3. 대상자 업그레이드 레벌 선정 및 업그레이드
	public void upgradeLevel(User user) throws SQLException {
		
		user.upgradeLevel(); // VO 부분에 기능을 만듦
		
		userDaoImpl.do_update(user);
		
//		sendUpgradeMail(user); // mail send
	
		
	}//--upgradeLevel
	
	/**비밀번호 찾기 메일 전송*/
	public void sendPasswdMail(User user) {
		
		try {
			
			// 보내는 사람
			String host = "smtp.naver.com";
			final String userName = "sytemp1234";
			final String password = "비밀번호넣기";
			int port = 465;
			
			// 받는 사람
			String recipient = user.getRid();
			
			// 제목
			String title = user.getUname() + "님의 비밀번호";
			
			// 내용
			String contents = user.getUname() + "님의 비밀번호는 " + user.getPasswd() + "입니다.";
			
			// SMTP 서버 설정
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);
			
			// 인증
			Session session = Session.getInstance(props, new Authenticator() {
		
				String uName = userName;
				String passwd = password;

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(uName, passwd);
				}
				
			});
			
			SimpleMailMessage mimeMessage = new SimpleMailMessage();
			
			// 보내는 사람
			mimeMessage.setFrom("sytemp1234@naver.com");
		
			// 받는 사람
			mimeMessage.setTo(recipient);
			
			// 제목
			mimeMessage.setSubject(title);
			
			// 내용
			mimeMessage.setText(contents);
			
			this.mailSender.send(mimeMessage);
			
				
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		LOG.info("===============================");
		LOG.info("=mail send=");
		LOG.info("===============================");
		
	}//--sendUpgradeMail
	
	
	@Override
	public User get_selectOne(DTO dto) {
		
		LOG.debug("==========================");
		LOG.debug("=@Service=" + dto);
		LOG.debug("==========================");
		
		return (User) userDaoImpl.get_selectOne(dto);
	}

	@Override
	public int do_update(DTO dto) {
		
		return userDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		
		return userDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		
		User user = (User) dto;
		
		if(null == user.getLvl()) { // user의 레벨이 없으면
			
			user.setLvl(Level.BASIC); // 레벨을 BASIC으로 설정
		}
		
		return userDaoImpl.do_save(user); // 해당 user를 등록
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get_excelDown(User vo, String ext) {

		// 헤더를 리스트로 만들고
		List<String> headers = Arrays.asList("아이디"
											,"비밀번호"
											,"이름"
											,"생년월일"
											,"휴대폰번호"
											,"우편번호"
											,"주소"
											,"자세한주소"
											,"레벨"
											,"포인트"
											,"이미지번호"
											,"등록일");
		
		// 데이터 조회해서 리스트로 만듦
		List<User> list = (List<User>) userDaoImpl.get_selectOne(vo);
		
		String saveFileNm = "";
		
		// 확장자에 따라 excelWriter의 엑셀 파일 생성 메소드를 호출
		if(ext.equalsIgnoreCase("csv")) {
			
			saveFileNm = excelWriter.csvWriterGeneralization(list, headers);
			
		}
		else if(ext.equalsIgnoreCase("xlsx")) {
			
			saveFileNm = excelWriter.xlsxWriterGeneralization(list, headers);
		}
		else if(ext.equalsIgnoreCase("xls")) {
			
			saveFileNm = excelWriter.xlsWriterGeneralization(list, headers);
		}
		
		return saveFileNm; // 생성한 엑셀 파일의 저장파일명을 반환
		
	}//--get_excelDown

	@Override
	public DTO idPassCheck(DTO dto) {
		
		// 메시지 객체 생성
		Message outMsg = new Message();
		
		//------1. 아이디 체크-------
		int flag = userDaoImpl.id_check(dto);
		
		if(flag < 1) { // 아이디 확인 실패
			
			outMsg.setMsgId("10");
			outMsg.setMsgMsg("아이디를 확인하시오");
			
			return outMsg;

		}
		
		//------2. 비밀번호 체크------
		flag = userDaoImpl.passwd_check(dto);
		
		if(flag < 1) { // 비밀번호 확인 실패
			
			outMsg.setMsgId("20");
			outMsg.setMsgMsg("비밀번호를 확인하시오");
			
			return outMsg;

		}
		
		//------3. 로그인 성공 체크------
		if(flag == 1) { // 로그인 성공 
			
			outMsg.setMsgId("30");			
		}
		
		LOG.debug("=========================");
		LOG.debug("=outMsg=" + outMsg);
		LOG.debug("=========================");
		
		return outMsg;
		
	}//--idPassCheck


}//--class
