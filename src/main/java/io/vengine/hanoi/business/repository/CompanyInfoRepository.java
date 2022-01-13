package io.vengine.hanoi.business.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.business.entity.CompanyInfo;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {

	CompanyInfo findByCompanyCode(String companyCode);

	@Transactional
	void deleteByCompanyCode(String companyCode);

	@Query("select a from CompanyInfo a where a.companyCode like %:searchKeyWord% or a.companyName like %:searchKeyWord% or a.address like %:searchKeyWord% ")
	Page<CompanyInfo> getItemBySearchKeyWord(String searchKeyWord, Pageable pageable);

}
