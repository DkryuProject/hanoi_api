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

import io.vengine.hanoi.common.entity.Screen;
import io.vengine.hanoi.common.service.ScreenService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/screen")
public class ScreenController {

	@Autowired
	ScreenService screenService;

	@PostMapping("/insert")
	public List<Screen> insertInfo(@RequestBody List<Screen> data) {
		screenService.insertInfo(data);
		return data;
	}

	@PostMapping("/update")
	public List<Screen> updateInfo(@RequestBody List<Screen> data) {
		screenService.updateInfo(data);
		return data;
	}

	@PostMapping("/delete")
	public List<Screen> deleteInfo(@RequestBody List<Screen> data) {
		for (Screen map : data) {
			screenService.deleteInfo(map);
		}
		return data;
	}

	@GetMapping("/getHeaders")
	public List<Screen> getHeaders(@RequestParam String screenId) {
		return screenService.getHeaders(screenId, "EN");
	}

	@GetMapping("/getItem")
	public Page<Screen> getItem(@RequestParam String searchKeyWord, Pageable pageable) {
		return screenService.getItem(searchKeyWord, pageable);
	}
}
