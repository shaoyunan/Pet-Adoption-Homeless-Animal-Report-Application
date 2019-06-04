package com.myspring.petfinder.xls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.myspring.petfinder.pojo.HomelessReport;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XlsView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<HomelessReport> list = (List<HomelessReport>)model.get("list");
		
		Sheet sheet = workbook.createSheet("Reports");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ReportID");
        header.createCell(1).setCellValue("Priority");
        header.createCell(2).setCellValue("Message");
        header.createCell(3).setCellValue("AnimalType");
        header.createCell(4).setCellValue("HealthCondition");
        header.createCell(5).setCellValue("ReportDate");
        header.createCell(6).setCellValue("Location");
        header.createCell(7).setCellValue("Aggressive");
        header.createCell(8).setCellValue("InDanger");
        int rowNum=1;
        for(HomelessReport r : list) {
        	Row row = sheet.createRow(rowNum++);
        	row.createCell(0).setCellValue(r.getReportid());
            row.createCell(1).setCellValue(r.getPriority().toString());
            row.createCell(2).setCellValue(r.getMessage());
            row.createCell(3).setCellValue(r.getAnimaltype());
            row.createCell(4).setCellValue(r.getHealthcondition().toString());
            row.createCell(5).setCellValue(r.getReportdate());
            row.createCell(6).setCellValue(r.getLocation());
            row.createCell(7).setCellValue(r.isAggressive());
            row.createCell(8).setCellValue(r.isIndanger());
        }
	}

}
