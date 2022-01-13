package io.vengine.hanoi.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.Login;
import io.vengine.hanoi.common.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findByAccessToken(String accessToken);

	List<Login> findByUser(User map);

}
