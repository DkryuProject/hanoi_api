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

import io.vengine.hanoi.common.entity.Menu;
import io.vengine.hanoi.common.service.MenuService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	@PostMapping("/insert")
	public List<Menu> insertMenu(@RequestBody List<Menu> data) {
		menuService.insertMenu(data);
		return data;
	}

	@GetMapping("/getItem")
	public Page<Menu> getMenuData(@RequestParam String searchKeyWord, Pageable pageable) {
		return menuService.getMenu(searchKeyWord, pageable);
	}

	@GetMapping("/getMenuList")
	public List<Menu> getMenuList(@RequestParam String menuType) {
		return menuService.getMenuList(menuType);
	}

	@PostMapping("/delete")
	public List<Menu> deleteMenu(@RequestBody List<Menu> data) {
		for (Menu map : data) {
			menuService.deleteMenu(map);
		}
		return data;
	}
}
