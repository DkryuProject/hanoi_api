package io.vengine.hanoi.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.common.entity.Screen;
import io.vengine.hanoi.common.repository.ScreenRepository;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	ScreenRepository screenRepository;

	@Override
	public void insertInfo(List<Screen> data) {
		screenRepository.saveAll(data);
	}

	@Override
	public List<Screen> getHeaders(String screenId, String language) {
		return screenRepository.findByScreenIdAndLanguageOrderBySeq(screenId, language);
	}

	@Override
	public Page<Screen> getItem(String searchKeyWord, Pageable pageable) {
		return screenRepository.getItemBySearchKeyWord(searchKeyWord, pageable);
	}

	@Override
	public void updateInfo(List<Screen> data) {
		Screen updateData = null;

		int row = 0;
		for (Screen map : data) {
			updateData = new Screen();
			updateData = screenRepository.findByScreenIdAndLanguageAndSeq(map.getScreenId(), map.getLanguage(), map.getSeq());
			updateData = data.get(row);

			screenRepository.save(updateData);

			row++;
		}
	}

	@Transactional
	@Override
	public void deleteInfo(Screen data) {
		screenRepository.delete(data);
	}

}
