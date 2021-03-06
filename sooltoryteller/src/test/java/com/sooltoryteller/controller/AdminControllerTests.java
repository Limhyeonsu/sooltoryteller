package com.sooltoryteller.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class AdminControllerTests {

	@Setter(onMethod_ =@Autowired )
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void serup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testRegisterLiq() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/admin/liq-register")
				.param("liqCoNm",  "명세주가")
				.param("nm",  "1")
				.param("capct",  "1")
				.param("cate",  "1")
				.param("img",  "1")
				.param("irdnt",  "1")
				.param("liqImg",  "1")
				.param("liqThumb",  "1")
				.param("lv",  "1")
				.param("liqCn.intro",  "1")
				.param("liqCnt.revwCnt", "0")
				).andReturn().getModelAndView().getModelMap());
	}
	
	//@Test
	public void testListPaging() throws Exception{
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/admin/memberlist")
				.param("pageNum",  "1")
				.param("amount",  "10"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	//@Test
	public void testFaqList()throws Exception{
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/admin/faqlist"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
}
