package org.example.spring.web.controller;

import org.example.spring.SampleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 *
 *  简单mvc 请求接口
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private SampleEntity sampleEntity;

	@GetMapping("/get")
	public ResponseEntity<Map<String, String>> home(@RequestHeader Map<String, String> header) {
		System.out.println(sampleEntity.toString());
		int i = 1/0;
		return ResponseEntity.of(Optional.of(header));
	}
}
