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

import io.vengine.hanoi.common.entity.DocumentNumber;
import io.vengine.hanoi.common.service.DocumentUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/documentNumber")
public class DocumentNumberController {

	@Autowired
	private DocumentUtil documentUtil;

	@PostMapping("/insert")
	public List<DocumentNumber> insertDocNo(@Valid @RequestBody List<DocumentNumber> items) {
		System.out.println("Document Number Save");
		return documentUtil.insertDocNo(items);
	}

	@GetMapping("/getData")
	public String getDocNumber() {
		return documentUtil.getDocNumber("FINANCE");
	}
}
