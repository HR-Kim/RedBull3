package kr.co.redbull.mypage.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.redbull.mypage.service.MyGoodService;

public class MyGoodController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyGoodService myGoodService;
	
	private final String VIEW_DETAIL  ="mypage/mygood";
	
}
