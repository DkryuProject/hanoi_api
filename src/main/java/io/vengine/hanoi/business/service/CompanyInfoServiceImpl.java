package io.vengine.hanoi.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.business.entity.CompanyInfo;
import io.vengine.hanoi.business.repository.CompanyInfoRepository;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

	@Autowired
	CompanyInfoRepository companyInfoRepository;

	@Override
	public List<CompanyInfo> insertCompanyInfo(List<CompanyInfo> item) {
		return companyInfoRepository.saveAll(item);
	}

	@Override
	public Page<CompanyInfo> getCompanyInfo(String searchKeyWord, Pageable pageable) {

		Page<CompanyInfo> result = null;

		if (!"".equals(searchKeyWord)) {
			result = companyInfoRepository.getItemBySearchKeyWord(searchKeyWord, pageable);
		} else {
			result = companyInfoRepository.findAll(pageable);
		}
		return result;
	}

	@Override
	public List<CompanyInfo> getCompanyInfoList() {
		return companyInfoRepository.findAll();
	}

	@Override
	public void updateCompanyInfo(List<CompanyInfo> item) {
		CompanyInfo updateData = null;

		int row = 0;
		for (CompanyInfo map : item) {
			updateData = new CompanyInfo();
			updateData = companyInfoRepository.findByCompanyCode(map.getCompanyCode());
			updateData = item.get(row);

			companyInfoRepository.save(updateData);

			row++;
		}
	}

	@Override
	public void deleteCompanyInfo(CompanyInfo item) {
		companyInfoRepository.deleteByCompanyCode(item.getCompanyCode());
	}

}
