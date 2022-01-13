package io.vengine.hanoi.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.business.entity.AccountInfo;

public interface AccountInfoService {

	public List<AccountInfo> insertAccountInfo(List<AccountInfo> item);

	public Page<AccountInfo> getAllAccountInfo(String searchKeyWord, Pageable pageable);

	public void updateAccountInfo(List<AccountInfo> item);

	public void deleteAccountInfo(AccountInfo item);

	public List<String> getCategoryList(String reportType);

	public List<String> getSubCategoryList(String string, String category);

	List<Map<String, Object>> findAccountTreeView();
}
