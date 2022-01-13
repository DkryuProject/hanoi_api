package io.vengine.hanoi.common.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vengine.hanoi.common.entity.Code;
import io.vengine.hanoi.common.service.CodeService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/code")
public class CodeController {

	@Autowired
	CodeService codeService;

	@PostMapping("/getItem")
	public List<Code> getItem(@RequestBody Code item) {
		System.out.println("Finance Code List Select");
		return codeService.getItem(item);
	}

	@GetMapping("/getAllItem")
	public List<Code> getAllItem() {
		System.out.println("Finance Code All Select");
		return codeService.getAllItem();
	}

	@PostMapping("/insert")
	public List<Code> insertCode(@Valid @RequestBody List<Code> item) {
		System.out.println("Finance Code Item Save");
		return codeService.insertCode(item);
	}
}
