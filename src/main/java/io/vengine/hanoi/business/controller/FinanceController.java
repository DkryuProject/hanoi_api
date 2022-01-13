package io.vengine.hanoi.business.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import io.vengine.hanoi.response.model.CommonResult;
import io.vengine.hanoi.response.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import io.vengine.hanoi.business.entity.FinanceDetail;
import io.vengine.hanoi.business.entity.FinancePriceDetail;
import io.vengine.hanoi.business.service.FinanceService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/financeDetail")
public class FinanceController {
	@Autowired
	ResponseService responseService;

	@Autowired
	private FinanceService financeService;

	@PostMapping("/getItem")
	public Page<FinanceDetail> getItem(@RequestBody Map<String, Object> item) {
		System.out.println("Finance Detail Select");
		return financeService.getItem(item);
	}

	@PostMapping("/insert")
	public List<FinanceDetail> insertFinanceDetail(@RequestBody List<FinanceDetail> items) {
		System.out.println("Finance Detail Save");
		financeService.insertFinanceDetail(items);
		return items;
	}

	@PostMapping("/fileUpload")
	public CommonResult fileUpload(@Valid @RequestBody List<FinanceDetail> items) {
		System.out.println("Finance Detail File Upload");
		financeService.fileUpload(items);
		return responseService.getSuccessResult();
	}

	@PostMapping("/update")
	public List<FinanceDetail> updateFinanceDetaill(@Valid @RequestBody List<FinanceDetail> items) {
		System.out.println("Finance Detail Update");
		financeService.updateFinanceDetaill(items);
		return items;
	}

	@PostMapping("/getItemDocNos")
	public List<FinanceDetail> getFinanceDetailDocNos(@RequestBody List<String> items) {
		System.out.println("Finance Detail Select");
		return financeService.getFinanceDetailDocNos(items);
	}

	@PostMapping("/deleteDocNos")
	public List<String> deleteFinanceDetaill(@Valid @RequestBody List<String> items) {
		System.out.println("Finance Detail Delete");
		financeService.updateDelFlagDOcNos(items);
		return items;
	}

	@Transactional
	@PostMapping("/deleteDocNoAndNum")
	public FinanceDetail deleteFinanceDetaillDocNoAndNum(@Valid @RequestBody FinanceDetail item) {
		System.out.println("Finance Detail Delete");
		financeService.deleteFinanceDetaillDocNoAndNum(item);
		return item;
	}

	@PostMapping("/financePriceDetailDelete")
	public FinancePriceDetail financePriceDetailDelete(@Valid @RequestBody FinancePriceDetail item) {
		financeService.deletePriceDetail(item);
		return item;
	}

	@PostMapping("/financePriceDetailUpdate")
	public Collection<FinancePriceDetail> financePriceDetailUpdate(@Valid @RequestBody FinanceDetail item) {
		financeService.updatePriceDetail(item);
		return item.getFinancePriceDetails();
	}

	@GetMapping("/getFinancePriceList")
	public Page<Object> getFinancePriceList(@RequestParam String searchKeyWord, Pageable pageable) {
		return financeService.getFinancePriceList(searchKeyWord, pageable);
	}

	@Transactional
	@GetMapping("/deleteAll")
	public void deleteAll() {
		System.out.println("Finance Detail Delete");
		financeService.deleteAll();
	}

	@GetMapping("/totalBalance/{accountCode}")
	public BigDecimal totalBalance(
			@PathVariable String accountCode
	) {
		System.out.println("Finance Detail totalBalance");
		return financeService.totalBalance(accountCode);
	}
}
