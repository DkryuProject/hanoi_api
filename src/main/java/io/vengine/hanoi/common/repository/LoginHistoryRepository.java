package io.vengine.hanoi.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.Login;
import io.vengine.hanoi.common.entity.LoginHistory;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

	List<LoginHistory> findByLogin(Login logindata);

}
