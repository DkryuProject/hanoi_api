package io.vengine.hanoi.common.service;

import java.util.List;

import io.vengine.hanoi.common.entity.DocumentNumber;

public interface DocumentUtil {

	public String getDocNumber(String Type);

	public List<DocumentNumber> insertDocNo(List<DocumentNumber> items);
}
