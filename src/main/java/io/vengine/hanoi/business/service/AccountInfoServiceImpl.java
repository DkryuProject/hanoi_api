package io.vengine.hanoi.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.business.entity.AccountInfo;
import io.vengine.hanoi.business.repository.AccountInfoRepository;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Override
	public List<AccountInfo> insertAccountInfo(List<AccountInfo> item) {
		return accountInfoRepository.saveAll(item);
	}

	@Override
	public Page<AccountInfo> getAllAccountInfo(String searchKeyWord, Pageable pageable) {

		Page<AccountInfo> result = null;

		if (!"".equals(searchKeyWord)) {
			result = accountInfoRepository.getItemBySearchKeyWord(searchKeyWord, pageable);
		} else {
			result = accountInfoRepository.findAll(pageable);
		}

		return result;
	}

	@Override
	public void updateAccountInfo(List<AccountInfo> item) {

		AccountInfo updateData = null;

		int row = 0;
		for (AccountInfo map : item) {
			updateData = new AccountInfo();
			updateData = accountInfoRepository.findByAccountCode(map.getAccountCode());
			updateData = item.get(row);

			accountInfoRepository.save(updateData);

			row++;
		}
	}

	@Override
	public void deleteAccountInfo(AccountInfo item) {
		accountInfoRepository.deleteByAccountCode(item.getAccountCode());
	}

	@Override
	public List<String> getCategoryList(String reportType) {
		return accountInfoRepository.getCategoryList(reportType);
	}

	@Override
	public List<String> getSubCategoryList(String reportType, String category) {
		return accountInfoRepository.getSubCategoryList(reportType, category);
	}

	@Override
	public List<Map<String, Object>> findAccountTreeView() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<String> categoryList = accountInfoRepository.findAllCategory();

		Map<String, Object> categoryMap = null;
		//Category
		for (String category : categoryList){
			categoryMap = new HashMap<>();

			//Sub Category
			List<String> subCategoryList = accountInfoRepository.findAllSubCategory(category);
			List<Map<String, Object>> subCategoryMapList = new ArrayList<>();
			for (String subCategory: subCategoryList){
				Map<String, Object> subCategoryMap = new HashMap<>();

				//1 Depth
				List<AccountInfo> oneDepthList = accountInfoRepository.findBySubCategoryAndParentIsNull(subCategory);
				List<Map<String, Object>> oneDepthMapList = new ArrayList<>();
				for(AccountInfo oneDepth : oneDepthList){
					Map<String, Object> oneDepthMap = new HashMap<>();

					//2 Depth
					List<AccountInfo> twoDepthList = accountInfoRepository.findByParent(oneDepth.getAccountCode());
					List<Map<String, Object>> twoDepthMapList = new ArrayList<>();
					for(AccountInfo twoDepth: twoDepthList){
						Map<String, Object> twoDepthMap = new HashMap<>();

						//3 Depth
						List<AccountInfo> thirdDepthList = accountInfoRepository.findByParent(twoDepth.getAccountCode());
						List<Map<String, Object>> thirdDepthMapList = new ArrayList<>();
						for(AccountInfo thirdDepth: thirdDepthList){
							Map<String, Object> thirdDepthMap = new HashMap<>();
							thirdDepthMap.put("id", thirdDepth.getAccountCode());
							thirdDepthMap.put("name", thirdDepth.getSubject()+"("+thirdDepth.getAccountCode()+")");
							thirdDepthMapList.add(thirdDepthMap);
						}
						twoDepthMap.put("id", twoDepth.getAccountCode());
						twoDepthMap.put("name", twoDepth.getSubject()+"("+twoDepth.getAccountCode()+")");
						twoDepthMap.put("children",thirdDepthMapList);
						twoDepthMapList.add(twoDepthMap);
					}
					oneDepthMap.put("id", oneDepth.getAccountCode());
					oneDepthMap.put("name", oneDepth.getSubject()+"("+oneDepth.getAccountCode()+")");
					oneDepthMap.put("children",twoDepthMapList);
					oneDepthMapList.add(oneDepthMap);
				}
				subCategoryMap.put("id", subCategory);
				subCategoryMap.put("name", subCategory);
				subCategoryMap.put("children",oneDepthMapList);
				subCategoryMapList.add(subCategoryMap);
			}
			categoryMap.put("id", category);
			categoryMap.put("name", category);
			categoryMap.put("children",subCategoryMapList);
			resultList.add(categoryMap);
		}
		return resultList;
	}

}
