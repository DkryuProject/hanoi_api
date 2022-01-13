package io.vengine.hanoi.common.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vengine.hanoi.common.entity.DocumentNumber;
import io.vengine.hanoi.common.repository.DocumentNumberRepository;

@Service
public class DocumentUtilImpl implements DocumentUtil {

	@Autowired
	private DocumentNumberRepository documentNumberRepository;

	@Override
	public String getDocNumber(String Type) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String today = dateFormat.format(System.currentTimeMillis());

		DocumentNumber documentNumber = documentNumberRepository.findByType(Type);

		int seq = documentNumber.getSeq() + 1;

		documentNumber.setDate(today);
		documentNumber.setSeq(seq);
		documentNumber.setDocNo(today + String.format("%04d", seq));

		documentNumberRepository.save(documentNumber);

		return documentNumberRepository.findByType(Type).getDocNo();
	}

	@Override
	public List<DocumentNumber> insertDocNo(List<DocumentNumber> items) {
		return documentNumberRepository.saveAll(items);
	}

}
