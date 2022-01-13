package io.vengine.hanoi.business.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vengine.hanoi.business.entity.AccountInfo;
import io.vengine.hanoi.business.service.AccountInfoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/accountInfo")
public class AccountInfoController {

	@Autowired
	AccountInfoService accountInfoService;

	@PostMapping("/insert")
	public List<AccountInfo> insertAccountInfo(@Valid @RequestBody List<AccountInfo> item) {
		System.out.println("Account Info Save");
		return accountInfoService.insertAccountInfo(item);
	}

	@GetMapping("/getItem")
	public Page<AccountInfo> getAllAccountInfo(@RequestParam String searchKeyWord, Pageable pageable) {
		System.out.println("Account Info Select");
		return accountInfoService.getAllAccountInfo(searchKeyWord, pageable);
	}

	@PostMapping("/update")
	public List<AccountInfo> updateAccountInfo(@Valid @RequestBody List<AccountInfo> item) {
		System.out.println("Account Info Update");
		accountInfoService.updateAccountInfo(item);
		return item;
	}

	@PostMapping("/deleteList")
	public List<AccountInfo> deleteAccountInfo(@Valid @RequestBody List<AccountInfo> item) {
		System.out.println("Account Info Delete List");
		for (AccountInfo map : item) {
			accountInfoService.deleteAccountInfo(map);
		}
		return item;
	}

	@PostMapping("/delete")
	public AccountInfo deleteAccountInfo(@Valid @RequestBody AccountInfo item) {
		System.out.println("Account Info Delete");
		accountInfoService.deleteAccountInfo(item);
		return item;
	}

	@GetMapping("/tree/view")
	public List<Map<String, Object>> findAccountTreeView() {
		System.out.println("Account Tree view");
		return accountInfoService.findAccountTreeView();
	}
}
