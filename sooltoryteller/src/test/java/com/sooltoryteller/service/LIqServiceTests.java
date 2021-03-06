package com.sooltoryteller.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sooltoryteller.domain.LiqVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LIqServiceTests {

	@Setter(onMethod_ = @Autowired)
	private LiqService service;

	public void testGetLiq() {
		LiqVO liq = service.get(2L);
		log.info(liq);

	}
}
