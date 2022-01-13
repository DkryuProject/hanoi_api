package io.vengine.hanoi.business.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.business.entity.FinanceDetail;
import io.vengine.hanoi.business.entity.FinancePriceDetail;

public interface FinanceService {

	public Page<FinanceDetail> getItem(Map<String, Object> item);

	public void insertFinanceDetail(List<FinanceDetail> items);

	public List<FinanceDetail> fileUpload(List<FinanceDetail> items);

	public void updateFinanceDetaill(List<FinanceDetail> item);

	public List<FinanceDetail> getFinanceDetailDocNos(List<String> data);

	public void updateDelFlagDOcNos(List<String> data);

	public void deleteFinanceDetaillDocNoAndNum(FinanceDetail item);

	public void deletePriceDetail(FinancePriceDetail item);

	public FinanceDetail updatePriceDetail(FinanceDetail item);

	public void deleteAll();

	public Page<Object> getFinancePriceList(String searchKeyWord, Pageable pageable);

	public BigDecimal getAmount(String reportType, String category, String subCategory, String year);

	BigDecimal totalBalance(String accountCode);
}
