package io.vengine.hanoi.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

	@Value("${download.financedetail.file.path}")
	private String downloadFinanceDetailFilePath;

	@Value("${download.financeprice.file.path}")
	private String downloadFinancePriceFilePath;

	@PostMapping("/download")
	public Resource download(@RequestBody Map<String, String> map) throws IOException {
		String filePath = "";
		if ("detail".equals(map.get("type"))) {
			filePath = downloadFinanceDetailFilePath;
		} else {
			filePath = downloadFinancePriceFilePath;
		}
		File file = new File(filePath);
		InputStream is = FileUtils.openInputStream(file);
		return new InputStreamResource(is);
	}

}
