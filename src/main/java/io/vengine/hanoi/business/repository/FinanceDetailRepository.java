package io.vengine.hanoi.business.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.icu.math.BigDecimal;

import io.vengine.hanoi.business.entity.AccountInfo;
import io.vengine.hanoi.business.entity.CompanyInfo;
import io.vengine.hanoi.business.entity.FinanceDetail;
import io.vengine.hanoi.business.entity.FinancePriceDetail;

@Repository
public interface FinanceDetailRepository extends JpaRepository<FinanceDetail, Long> {

	List<FinanceDetail> findByAccountCode(AccountInfo accountCode);

	List<FinanceDetail> findByDocNo(String docNo);

	@Query("SELECT a FROM FinanceDetail a WHERE a.docNo IN (:data) AND a.delFlag='N' order by a.date desc, a.docNo desc, a.lineNum")
	List<FinanceDetail> findByFinanceDetailDocNos(List<String> data);

	@Query("SELECT IFNULL(max(a.lineNum),0)+1 FROM FinanceDetail a WHERE a.docNo = :data")
	long findByFinanceDetailDocNoLineNumberMax(String data);

	@Modifying
	@Transactional
	@Query("UPDATE FinanceDetail a SET a.delFlag='Y' WHERE a.docNo IN (:data)")
	void updateDelFlagDOcNos(List<String> data);

	FinanceDetail findByDocNoAndLineNum(String docNo, long lineNum);

	Page<FinanceDetail> findByDocNoAndDelFlag(String docNo, String delFlag, Pageable pageable);

	Page<FinanceDetail> findByAccountCodeAndDelFlag(AccountInfo accountInfo, String delFlag, Pageable pageable);

	Page<FinanceDetail> findByCompanyCodeAndDelFlag(CompanyInfo companyInfo, String delFlag, Pageable pageable);

	Page<FinanceDetail> findByDelFlag(String delFlag, Pageable pageable);

	Page<FinanceDetail> findByInsertUserAndDelFlag(String insertUser, String delFlag, Pageable pageable);

	Page<FinanceDetail> findByDelFlagAndDateBetween(String delFlag, String fromDate, String toDate, Pageable pageable);

	Page<FinanceDetail> findByAccountCodeAndDelFlagAndDateBetween(AccountInfo accountInfo, String delFlag, String fromDate, String toDate, Pageable pageable);

	Page<FinanceDetail> findByCompanyCodeAndDelFlagAndDateBetween(CompanyInfo companyInfo, String delFlag, String fromDate, String toDate, Pageable pageable);

	@Query("SELECT a.docNo, a.companyCode, b FROM FinanceDetail a JOIN a.financePriceDetails b")
	Page<Object> getFinancePriceDetails(Pageable pageable);

	@Query("SELECT a.docNo, a.companyCode, b FROM FinanceDetail a JOIN a.financePriceDetails b on a.docNo like %:searchKeyWord% or a.companyCode like %:searchKeyWord% or b.subCategoryName like %:searchKeyWord%")
	Page<Object> getFinancePriceList(String searchKeyWord, Pageable pageable);

	@Query("SELECT coalesce(sum(coalesce(a.debit,0)) - sum(coalesce(a.credit,0)),0) FROM FinanceDetail a JOIN a.accountCode b on a.delFlag='N' and b.report= :reportType and b.category= :category and b.subCategory= :subCategory and a.date like :year% ")
	long getAmount(String reportType, String category, String subCategory, String year);

	List<FinanceDetail> findByAccountCodeAndDelFlag(AccountInfo accountInfo, String n);
}