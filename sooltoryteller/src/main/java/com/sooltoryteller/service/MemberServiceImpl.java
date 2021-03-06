package com.sooltoryteller.service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sooltoryteller.domain.MemberVO;
import com.sooltoryteller.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mapper;
	
	
	
	@Transactional
	@Override
	public boolean join(MemberVO member) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(member != null) {
			//중복된 이메일, 중복된 닉네임이 있다면
			if(checkEmail(member.getEmail()) >=1 || checkName(member.getName())>=1) {
				return false;
			}
		String encPwd = encoder.encode(member.getPwd());
		member.setPwd(encPwd);
		System.out.println("암호화된 비번 : " + encPwd);
		
		mapper.insert(member);
		mapper.insertHist(mapper.read(member.getEmail()));
	}
				
		return true;
	}

	@Override
	public boolean loginCheck(String email, String pwd) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String result = mapper.getPwd(email);
		System.out.println("비밀번호 꺼내오기 : "+result);
		
		if(result !=null) {
			if(encoder.matches(pwd, result)) {
				if(mapper.getRegStus(email).equalsIgnoreCase("JN")) {
				return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public MemberVO get(String email) {
		log.info("get...."+email);
		return mapper.read(email);
		
	}

	@Transactional
	@Override
	public boolean modify(MemberVO member) {
		log.info("modify..."+member);
		
		
		boolean validName = Pattern.matches("^[가-힣]{2,8}$", member.getName());
		boolean validTelno = Pattern.matches("^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$", member.getTelno());

		if(!validName) {
			return false;
		}
		
		if(!validTelno) {
			return false;
		}
		
		
		if(mapper.updateInfo(member) == 1) {
			mapper.insertHist(mapper.read(member.getEmail()));
			return true;
		}
		return false;
	}

	@Override
	public List<MemberVO> getList() {
		log.info("getList.....");
		return mapper.getList();
	}

	@Override
	public boolean modifyRegStus(String email) {

		return mapper.updateRegstus(email) == 1;
	}

	@Override
	public int checkEmail(String email) {
		log.info("이메일~~~"+email);
		return mapper.checkEmail(email);
	}

	@Override
	public String getPwd(String email) {
		
		String pwd = "";
		if(email != null) {
			pwd = mapper.getPwd(email);
		}
		
		return pwd;
	}

	@Transactional
	@Override
	public boolean modifyPwd(String email, String pwd) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		boolean validPwd = Pattern.matches("^(?=.*[a-zA-Z])(?=.*[#?!@$%^&*-]).{5,16}$", pwd);
		
		String encPwd = encoder.encode(pwd);
		
		System.out.println("새로운 비밀번호 인코딩 : "+encPwd);
		
		if(validPwd && mapper.updatePwd(email, encPwd) == 1) {
			mapper.insertHist(mapper.read(email));
			return true;
		}
		
		return false;
	}

	@Override
	public Long getMemberId(String email) {
		return mapper.getMemberId(email);
	}

	@Override
	public int checkName(String name) {
		
		return mapper.checkName(name);
	}
	
	// 수빈
	// 회원 아이디 & 닉네임 불러오기
	@Override
	public MemberVO getMemberIdName(String email) {
		log.info("========== GET MEMBER ID & NAME : " + mapper.getMemberIdName(email) + " ==========");
		return mapper.getMemberIdName(email);
	}

	@Override
	public String getEmail(Long memberId) {
		
		return mapper.getEmail(memberId);
	}

	@Override
	public String getAuthority(String email) {
		return mapper.getAuthority(email);
	}

	@Override
	public boolean examinePwd(String email, String pwd) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = "";
		
		if(email != null) {
			result = mapper.getPwd(email);
		}
		
		if(encoder.matches(pwd, result)) {
			return true;
		}
		
		return false;
	}

}
