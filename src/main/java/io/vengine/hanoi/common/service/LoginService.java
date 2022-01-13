package io.vengine.hanoi.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.common.entity.Login;
import io.vengine.hanoi.common.entity.LoginHistory;
import io.vengine.hanoi.common.entity.User;

public interface LoginService {

	public void insertLogin(Login loginData);

	public void insertHistory(Login loginData);

	public List<Login> getLoginHistory();

	public List<LoginHistory> getHistory();

	public Login loginCheck(String data);

	public User userCheck(String data);

	public void userInsert(User user);

	public Page<User> userList(String searchKeyWord, Pageable pageable);

	public List<User> confirmUser(List<User> user);

	public List<User> deleteUser(List<User> user);
}
