package io.vengine.hanoi.common.service;

import java.util.List;

import io.vengine.hanoi.common.entity.Code;

public interface CodeService {

	public List<Code> getItem(Code item);

	public List<Code> getAllItem();

	public List<Code> insertCode(List<Code> item);
}
