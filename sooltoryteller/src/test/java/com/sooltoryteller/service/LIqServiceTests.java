package com.sooltoryteller.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sooltoryteller.domain.AdminCriteria;
import com.sooltoryteller.domain.LiqCnVO;
import com.sooltoryteller.domain.LiqCntVO;
import com.sooltoryteller.domain.LiqCoVO;
import com.sooltoryteller.domain.LiqVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LIqServiceTests {

	@Setter(onMethod_ =@Autowired )
	private LiqService service;
	
	@Test
	public void testInsert() {
		LiqVO liq = new LiqVO();
		liq.setNm("123");
		liq.setCapct(133);
		liq.setCate("탁주");
		liq.setImg("1234123");
		liq.setIrdnt("asdfa");
		liq.setLv(1);
		LiqCnVO cn = new LiqCnVO();
		
		cn.setIntro("12341234");
		LiqCntVO cnt = new LiqCntVO();
		service.registerLiq(liq, cn, 1L,cnt); 
		
	}
	
	public void testGetAll() {
		AdminCriteria adCri = new AdminCriteria();
		List<LiqVO> allLiq = service.getLiqListWithPaging(adCri);
		allLiq.forEach(liq->log.info(liq));
	}
	
	public void testGetLiq() {
		LiqVO liq = service.get(2L);
		log.info(liq);
		
	}
}
