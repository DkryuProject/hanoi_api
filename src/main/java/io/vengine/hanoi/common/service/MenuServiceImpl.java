package io.vengine.hanoi.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.common.entity.Menu;
import io.vengine.hanoi.common.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public void insertMenu(List<Menu> data) {
		for (Menu map : data) {
			menuRepository.save(map);
		}
	}

	@Override
	public Page<Menu> getMenu(String searchKeyWord, Pageable pageable) {
		return menuRepository.getItemBySearchKeyWord(searchKeyWord, pageable);
	}

	@Override
	public List<Menu> getMenuList(String menuType) {
		return menuRepository.findByMenuTypeOrderBySeq(menuType);
	}

	@Override
	@Transactional
	public void deleteMenu(Menu data) {
		menuRepository.delete(data);
	}

}
