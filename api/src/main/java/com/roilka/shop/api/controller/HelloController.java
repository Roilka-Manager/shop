package com.roilka.shop.api.controller;

import com.roilka.shop.common.util.DataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

	@GetMapping(value = "/getNum/{aa}")
	public String  getNum(String aa ){
		if (DataUtil.isEmptyOrNull(aa)){
			return "sss";
		}
		return aa+"sss";
	}
}
