package io.vengine.hanoi.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUserId(String data);

	@Query("select a from User a where a.userId like %:searchKeyWord% or a.name like %:searchKeyWord%")
	Page<User> userList(String searchKeyWord, Pageable pageable);

}
