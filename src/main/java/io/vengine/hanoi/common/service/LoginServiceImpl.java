package io.vengine.hanoi.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.common.entity.Login;
import io.vengine.hanoi.common.entity.LoginHistory;
import io.vengine.hanoi.common.entity.User;
import io.vengine.hanoi.common.repository.LoginHistoryRepository;
import io.vengine.hanoi.common.repository.LoginRepository;
import io.vengine.hanoi.common.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void insertLogin(Login loginData) {
		System.out.println("loginData=======" + loginData);
		User user = new User();
		user.setUserId(loginData.getEmail());

		loginData.setUser(user);
		loginRepository.save(loginData);
		insertHistory(loginData);
	}

	@Override
	public void insertHistory(Login loginData) {
		LoginHistory loginhistory = new LoginHistory();
		loginhistory.setLogin(loginData);
		loginHistoryRepository.save(loginhistory);
	}

	@Override
	public List<Login> getLoginHistory() {
		return loginRepository.findAll();
	}

	@Override
	public List<LoginHistory> getHistory() {
		return loginHistoryRepository.findAll();
	}

	@Override
	public Login loginCheck(String Data) {
		return loginRepository.findByAccessToken(Data);
	}

	@Override
	public void userInsert(User user) {
		userRepository.save(user);
	}

	@Override
	public User userCheck(String data) {
		return userRepository.findByUserId(data);
	}

	@Override
	public Page<User> userList(String searchKeyWord, Pageable pageable) {
		return userRepository.userList(searchKeyWord, pageable);
	}

	@Override
	public List<User> confirmUser(List<User> user) {
		User updateData = null;

		for (User map : user) {
			updateData = new User();
			updateData = userRepository.findByUserId(map.getUserId());
			updateData.setConfirmStatus(true);
			userRepository.save(updateData);
		}
		return null;
	}

	@Override
	public List<User> deleteUser(List<User> user) {
		for (User userData : user) {
			for (Login logindata : loginRepository.findByUser(userData)) {
				for (LoginHistory loginHistoryData : loginHistoryRepository.findByLogin(logindata)) {
					loginHistoryRepository.delete(loginHistoryData);
				}
				loginRepository.delete(logindata);
			}
			userRepository.delete(userData);
		}
		return user;
	}

}
