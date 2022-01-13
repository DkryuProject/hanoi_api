package io.vengine.hanoi.report.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.vengine.hanoi.business.service.AccountInfoService;
import io.vengine.hanoi.business.service.FinanceService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

	@Value("${report.jrxml.path}")
	String reportPath;

	@Autowired
	AccountInfoService accountInfoService;

	@Autowired
	private FinanceService financeService;

	@GetMapping("/finaceReport")
	@ResponseBody
	public void financeGenerateReport(@RequestParam String type ,HttpServletResponse response) throws Exception {
		try {
			System.out.println("type: "+type);
			String reportType="Income Statement";
			
			if("A".equals(type)) {
				reportType="Balance Sheet";
			} else {
				reportType="Income Statement";
			}
			
			List<String> categoryList = accountInfoService.getCategoryList(reportType);
			List<Report> reportList = new ArrayList<Report>();
			
			for (String catergoryData : categoryList) {
				System.out.println("category: " + catergoryData);
				BigDecimal preTotalAmount = new BigDecimal(0);
				BigDecimal currentTotalAmount = new BigDecimal(0);
				
				reportList.add(new Report(catergoryData, null, null));

				List<String> subCategoryList = accountInfoService.getSubCategoryList(reportType, catergoryData);

				for (String subCatergoryData : subCategoryList) {
					System.out.println("category: " + subCatergoryData);
					BigDecimal preAmount = financeService.getAmount(reportType, catergoryData, subCatergoryData, "2019");
					BigDecimal currentAmount = financeService.getAmount(reportType, catergoryData, subCatergoryData, "2020");
					
					reportList.add(new Report("  " + subCatergoryData, preAmount, currentAmount));
					preTotalAmount = preTotalAmount.add(preAmount);
					currentTotalAmount = currentTotalAmount.add(currentAmount);
				}
				reportList.add(new Report("    Total " + catergoryData, preTotalAmount, currentTotalAmount));
			}

			System.out.println("reportList: " + reportList);

			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "//financeDHG_"+type+".jrxml");

			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportList);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "Websparrow.org");
			// parameters.put("createdBy", "Websparrow.org");

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);

			// Export the report to a PDF file
			// Media Type
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			// Export PDF Stream
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
