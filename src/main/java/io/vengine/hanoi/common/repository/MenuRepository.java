package io.vengine.hanoi.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

	@Query("select a from Menu a where a.menuType like %:searchKeyWord% or a.menuName like %:searchKeyWord% or a.url like %:searchKeyWord% or a.screenId like %:searchKeyWord% ")
	Page<Menu> getItemBySearchKeyWord(String searchKeyWord, Pageable pageable);

	List<Menu> findByMenuTypeOrderBySeq(String menuType);
}
