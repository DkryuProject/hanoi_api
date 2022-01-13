package io.vengine.hanoi.business.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.business.entity.AccountInfo;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {

	AccountInfo findByAccountCode(String accountCode);

	@Transactional
	void deleteByAccountCode(String accountCode);

	@Query("select a from AccountInfo a where " + "a.accountCode like %:searchKeyWord% " + "or a.currency like %:searchKeyWord% "
			+ "or a.category like %:searchKeyWord% " + "or a.subCategory like %:searchKeyWord% " + "or a.subject like %:searchKeyWord% "
			+ "or a.localCode like %:searchKeyWord% ")
	Page<AccountInfo> getItemBySearchKeyWord(String searchKeyWord, Pageable pageable);

	@Query("select a.category from AccountInfo a where a.report= :reportType group by a.category")
	List<String> getCategoryList(String reportType);

	@Query("select a.subCategory from AccountInfo a where a.report= :reportType and a.category= :category group by a.subCategory")
	List<String> getSubCategoryList(String reportType, String category);

	@Query("select distinct a.category from AccountInfo a order by a.category")
	List<String> findAllCategory();

	@Query("select distinct a.subCategory from AccountInfo a where a.category= :category order by a.subCategory")
	List<String> findAllSubCategory(String category);

	List<AccountInfo> findBySubCategoryAndParentIsNull(String subCategory);

	List<AccountInfo> findByParent(String accountCode);
}
