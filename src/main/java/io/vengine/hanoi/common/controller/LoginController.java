package io.vengine.hanoi.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vengine.hanoi.common.entity.Login;
import io.vengine.hanoi.common.entity.LoginHistory;
import io.vengine.hanoi.common.entity.User;
import io.vengine.hanoi.common.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
	@Autowired
	LoginService loginService;

	@PostMapping("/insert")
	public Login insertLogin(@RequestBody Login loginData) {
		loginService.insertLogin(loginData);
		return loginData;
	}

	@PostMapping("/insertHistory")
	public Login insertHistory(@RequestBody Login loginData) {
		loginService.insertHistory(loginData);
		return loginData;
	}

	@GetMapping("/getData")
	public List<Login> getLoginHistory() {
		return loginService.getLoginHistory();
	}

	@GetMapping("/getHistory")
	public List<LoginHistory> getHistory() {
		return loginService.getHistory();
	}

	@PostMapping("/loginCheck")
	public Login loginCheck(@RequestBody Login loginData) {
		return loginService.loginCheck(loginData.getAccessToken());
	}

	@PostMapping("/userCheck")
	public User userCheck(@RequestBody User userData) {
		return loginService.userCheck(userData.getUserId());
	}

	@PostMapping("/userApprovalRequest")
	public User userInsert(@RequestBody User user) {
		loginService.userInsert(user);
		return user;
	}

	@GetMapping("/userList")
	public Page<User> userList(@RequestParam String searchKeyWord, Pageable pageable) {
		return loginService.userList(searchKeyWord, pageable);
	}

	@PostMapping("/confirmUser")
	public List<User> confirmUser(@RequestBody List<User> user) {
		return loginService.confirmUser(user);
	}

	@PostMapping("/deleteUser")
	public List<User> deleteUser(@RequestBody List<User> user) {
		return loginService.deleteUser(user);
	}
}
