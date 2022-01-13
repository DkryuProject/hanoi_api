package io.vengine.hanoi.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, String> {

	@Query("select a from Screen a where a.screenId like %:searchKeyWord% or a.text like %:searchKeyWord% or a.value like %:searchKeyWord% or a.language like %:searchKeyWord% ")
	Page<Screen> getItemBySearchKeyWord(String searchKeyWord, Pageable pageable);

	List<Screen> findByScreenIdAndLanguageOrderBySeq(String screenId, String language);

	Screen findByScreenIdAndLanguageAndSeq(String screenId, String language, long seq);

}
