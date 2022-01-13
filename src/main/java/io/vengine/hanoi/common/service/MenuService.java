package io.vengine.hanoi.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.common.entity.Menu;

public interface MenuService {

	public void insertMenu(List<Menu> data);

	public Page<Menu> getMenu(String searchKeyWord, Pageable pageable);

	public List<Menu> getMenuList(String menuType);

	public void deleteMenu(Menu data);
}
