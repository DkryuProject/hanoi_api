package io.vengine.hanoi.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import groovy.util.logging.Slf4j;
import io.vengine.hanoi.business.repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.business.entity.AccountInfo;
import io.vengine.hanoi.business.entity.CompanyInfo;
import io.vengine.hanoi.business.entity.FinanceDetail;
import io.vengine.hanoi.business.entity.FinancePriceDetail;
import io.vengine.hanoi.business.repository.FinanceDetailRepository;
import io.vengine.hanoi.business.repository.FinancePriceDetailRepository;
import io.vengine.hanoi.common.service.DocumentUtil;

@Service
@Slf4j
public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinanceDetailRepository financeDetailRepository;

	@Autowired
	private FinancePriceDetailRepository financePriceDetailRepository;

	@Autowired
	private DocumentUtil documentUtil;

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Override
	public Page<FinanceDetail> getItem(Map<String, Object> params) {
		System.out.println("Finance Detail Select: " + params);

		@SuppressWarnings("unchecked")
		Map<String, String> data = (Map<String, String>) params.get("data");
		@SuppressWarnings("unchecked")
		Map<String, String> page = (Map<String, String>) params.get("page");
		@SuppressWarnings("unchecked")
		ArrayList<String> fromtoDate = (ArrayList<String>) params.get("fromtoDate");

		String fromDate = "";
		String toDate = "";
		if (fromtoDate.size() != 0) {
			fromDate = fromtoDate.get(0).replaceAll("-", "");
			toDate = fromtoDate.get(1).replaceAll("-", "");
		}

		int pageNum = Integer.parseInt(String.valueOf(page.get("pageNum")));
		int size = Integer.parseInt(String.valueOf(page.get("size")));

		Pageable pageable = PageRequest.of(pageNum, size,
				Sort.by("date").descending().and(Sort.by("docNo")).descending().and(Sort.by("lineNum")).and(Sort.by("currency")));

		Page<FinanceDetail> result = null;

		try {
			if ("docNo".equals(data.get("selectType"))) {
				result = financeDetailRepository.findByDocNoAndDelFlag(data.get("selectCode"), "N", pageable);
			} else if ("accountCode".equals(data.get("selectType"))) {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountCode(data.get("selectCode"));
				if (fromtoDate.size() != 0) {
					result = financeDetailRepository.findByAccountCodeAndDelFlagAndDateBetween(accountInfo, "N", fromDate, toDate, pageable);
				} else {
					result = financeDetailRepository.findByAccountCodeAndDelFlag(accountInfo, "N", pageable);
				}
			} else if ("companyCode".equals(data.get("selectType"))) {
				CompanyInfo companyInfo = new CompanyInfo();
				companyInfo.setCompanyCode(data.get("selectCode"));
				if (fromtoDate.size() != 0) {
					result = financeDetailRepository.findByCompanyCodeAndDelFlagAndDateBetween(companyInfo, "N", fromDate, toDate, pageable);
				} else {
					result = financeDetailRepository.findByCompanyCodeAndDelFlag(companyInfo, "N", pageable);
				}
			} else {
				if (fromtoDate.size() != 0) {
					result = financeDetailRepository.findByDelFlagAndDateBetween("N", fromDate, toDate, pageable);
				} else {
					result = financeDetailRepository.findByDelFlag("N", pageable);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	@Transactional
	public void insertFinanceDetail(List<FinanceDetail> items) {
		try {
			for (FinanceDetail financeDetail : items) {
				financeDetail.setLineNum(financeDetailRepository.findByFinanceDetailDocNoLineNumberMax(financeDetail.getDocNo()));
				financeDetailRepository.save(financeDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public List<FinanceDetail> fileUpload(List<FinanceDetail> items) {

		String docNo = "";
		String subRowFlag = "";

		try {
			for (FinanceDetail map : items) {
				if (map.getLineNum() == 1) {
					docNo = documentUtil.getDocNumber("FINANCE");
					subRowFlag = "N";
				} else {
					subRowFlag = "Y";
				}
				map.setDocNo(docNo);
				map.setSubRowFlag(subRowFlag);

				financeDetailRepository.save(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public void updateFinanceDetaill(List<FinanceDetail> item) {
		FinanceDetail updateData = null;

		int row = 0;
		for (FinanceDetail map : item) {
			updateData = new FinanceDetail();
			updateData = financeDetailRepository.findByDocNoAndLineNum(map.getDocNo(), map.getLineNum());
			updateData = item.get(row);
			if (map.getLineNum() == 0) {
				long num = financeDetailRepository.findByFinanceDetailDocNoLineNumberMax(map.getDocNo());
				updateData.setLineNum(num);
			}
			updateData.getFinancePriceDetails().addAll(map.getFinancePriceDetails());

			financeDetailRepository.save(updateData);
			row++;
		}
	}

	@Override
	public List<FinanceDetail> getFinanceDetailDocNos(List<String> data) {
		return financeDetailRepository.findByFinanceDetailDocNos(data);
	}

	@Override
	public void updateDelFlagDOcNos(List<String> data) {
		financeDetailRepository.updateDelFlagDOcNos(data);
	}

	@Override
	public void deleteFinanceDetaillDocNoAndNum(FinanceDetail item) {
		FinanceDetail updateData = new FinanceDetail();
		updateData = financeDetailRepository.findByDocNoAndLineNum(item.getDocNo(), item.getLineNum());
		updateData.setDelFlag("Y");
		financeDetailRepository.save(updateData);
	}

	@Override
	public void deletePriceDetail(FinancePriceDetail item) {
		FinancePriceDetail updateData = new FinancePriceDetail();

		updateData = financePriceDetailRepository.findById(item.getId());
		financePriceDetailRepository.delete(updateData);
	}

	@Override
	public FinanceDetail updatePriceDetail(FinanceDetail item) {
		FinanceDetail updateData = new FinanceDetail();
		updateData = financeDetailRepository.findByDocNoAndLineNum(item.getDocNo(), item.getLineNum());
		updateData.setFinancePriceDetails((List<FinancePriceDetail>) item.getFinancePriceDetails());
		return financeDetailRepository.save(updateData);
	}

	@Override
	public void deleteAll() {
		financeDetailRepository.deleteAll();
		financePriceDetailRepository.deleteAll();
	}

	@Override
	public Page<Object> getFinancePriceList(String searchKeyWord, Pageable pageable) {
		Page<Object> result = null;

		if (!"".equals(searchKeyWord)) {
			result = financeDetailRepository.getFinancePriceList(searchKeyWord, pageable);
		} else {
			result = financeDetailRepository.getFinancePriceDetails(pageable);
		}
		return result;
	}

	@Override
	public BigDecimal getAmount(String reportType, String category, String subCategory, String year) {
		return new BigDecimal(financeDetailRepository.getAmount(reportType, category, subCategory, year));
	}

	@Override
	public BigDecimal totalBalance(String accountCode) {
		AccountInfo accountInfo = accountInfoRepository.findByAccountCode(accountCode);
		BigDecimal totalBalance = financeDetailRepository.findByAccountCodeAndDelFlag(accountInfo,"N")
				.stream()
				.map(item -> {
					BigDecimal balance = BigDecimal.ZERO;
					BigDecimal debit = BigDecimal.ZERO;
					BigDecimal credit = BigDecimal.ZERO;
					if(item.getDebit() != null){
						debit = item.getDebit();
					}
					if(item.getCredit() != null){
						credit = item.getCredit();
					}
					balance = debit.subtract(credit);
					return balance;
				}).reduce(BigDecimal.ZERO, (a, b)->a.add(b));
		return totalBalance;
	}
}
