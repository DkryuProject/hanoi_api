package io.vengine.hanoi.business.controller;

import java.util.List;

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

import io.vengine.hanoi.business.entity.CompanyInfo;
import io.vengine.hanoi.business.service.CompanyInfoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/company")
public class CompanyInfoController {

	@Autowired
	CompanyInfoService companyInfoService;

	@PostMapping("/insert")
	public List<CompanyInfo> insertCompanyInfo(@Valid @RequestBody List<CompanyInfo> item) {
		return companyInfoService.insertCompanyInfo(item);
	}

	@GetMapping("/getItem")
	public Page<CompanyInfo> getCompanyInfo(@RequestParam String searchKeyWord, Pageable pageable) {
		return companyInfoService.getCompanyInfo(searchKeyWord, pageable);
	}

	@GetMapping("/getList")
	public List<CompanyInfo> getCompanyInfoList() {
		return companyInfoService.getCompanyInfoList();
	}

	@PostMapping("/update")
	public List<CompanyInfo> updateCompanyInfo(@Valid @RequestBody List<CompanyInfo> item) {
		companyInfoService.updateCompanyInfo(item);
		return item;
	}

	@PostMapping("/deleteList")
	public List<CompanyInfo> deleteCompanyInfo(@Valid @RequestBody List<CompanyInfo> item) {
		for (CompanyInfo map : item) {
			companyInfoService.deleteCompanyInfo(map);
		}

		return item;
	}

	@PostMapping("/delete")
	public CompanyInfo deleteCompanyInfo(@Valid @RequestBody CompanyInfo item) {
		companyInfoService.deleteCompanyInfo(item);
		return item;
	}
}
