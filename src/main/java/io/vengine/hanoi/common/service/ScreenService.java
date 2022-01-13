package io.vengine.hanoi.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.vengine.hanoi.common.entity.Screen;

public interface ScreenService {

	public void insertInfo(List<Screen> data);

	public void updateInfo(List<Screen> data);

	public void deleteInfo(Screen data);

	public List<Screen> getHeaders(String screenId, String language);

	public Page<Screen> getItem(String searchKeyWord, Pageable pageable);

}
