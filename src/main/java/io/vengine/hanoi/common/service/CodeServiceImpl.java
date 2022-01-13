package io.vengine.hanoi.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.common.entity.Code;
import io.vengine.hanoi.common.repository.CodeRepository;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeRepository CodeRepository;

	@Override
	public List<Code> getItem(Code item) {
		return CodeRepository.findByTypeOrderBySeq(item.getType());
	}

	@Override
	public List<Code> getAllItem() {
		return CodeRepository.findAll();
	}

	@Override
	public List<Code> insertCode(List<Code> item) {
		return CodeRepository.saveAll(item);
	}

}
