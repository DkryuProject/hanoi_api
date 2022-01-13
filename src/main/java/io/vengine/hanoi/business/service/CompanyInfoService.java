package io.vengine.hanoi.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.business.entity.CompanyInfo;

public interface CompanyInfoService {

	public List<CompanyInfo> insertCompanyInfo(List<CompanyInfo> item);

	public Page<CompanyInfo> getCompanyInfo(String searchKeyWord, Pageable pageable);

	public List<CompanyInfo> getCompanyInfoList();

	public void updateCompanyInfo(List<CompanyInfo> item);

	public void deleteCompanyInfo(CompanyInfo item);
}
