package io.vengine.hanoi.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.vengine.hanoi.business.entity.FinancePriceDetail;

public interface FinancePriceDetailRepository extends JpaRepository<FinancePriceDetail, Long> {

	FinancePriceDetail findById(long id);

	@Query("select a from FinancePriceDetail a where " + "a.subCategoryName like %:searchKeyWord% " + "or a.currency like %:searchKeyWord% "
			+ "or a.supplier like %:searchKeyWord% ")
	Page<FinancePriceDetail> getFinancePriceList(String searchKeyWord, Pageable pageable);

}
