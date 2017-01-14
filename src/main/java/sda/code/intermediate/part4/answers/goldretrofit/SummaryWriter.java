package sda.code.intermediate.part4.answers.goldretrofit;

import static sda.code.intermediate.part4.answers.goldretrofit.Messages.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sda.code.intermediate.part4.answers.goldmodel.GoldPrice;

public class SummaryWriter {

	private File summaryFile;

	public SummaryWriter(String summaryLocation) {
		this.summaryFile = new File(summaryLocation);
	}

	public void write(List<GoldPrice> prices, String recommendation) {
		try (Workbook wb = new XSSFWorkbook(); OutputStream sout = new FileOutputStream(summaryFile)) {
			Sheet dataSheet = putData(wb, prices, recommendation);
			drawPlot(dataSheet);
			wb.write(sout);
			System.out.println(fmt(SUCCESSFUL_SUMMARY_SAVE, summaryFile.getAbsolutePath()));
		} catch (IOException e) {
			System.err.println(fmt(FAILED_SAVING_SUMMARY, e.getMessage()));
		}
	}

	private void drawPlot(Sheet dataSheet) {
		PlotDrawer.draw(dataSheet, 0, 1);
	}

	private Sheet putData(Workbook wb, List<GoldPrice> prices, String recommendation) {
		Sheet sheet = wb.createSheet(SHEET_NAME_DATA);
		fillSheet(sheet, prices, recommendation);
		return sheet;
	}

	private CellStyle createDateStyle(Workbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd"));
		return cellStyle;
	}

	private void fillSheet(Sheet sheet, List<GoldPrice> prices, String recommendation) {
		fillPrices(sheet, prices);

		Cell avg = sheet.getRow(0).createCell(3);
		avg.setCellFormula("AVERAGE(B1:B" + prices.size() + ")");

		Cell recom = sheet.getRow(0).createCell(4);
		recom.setCellValue(recommendation);

		Cell notice = sheet.getRow(2).createCell(3);
		notice.setCellValue(WARNING_NOTICE);
	}

	private void fillPrices(Sheet sheet, List<GoldPrice> prices) {
		int rownum = 0;
		for (GoldPrice price : prices) {
			fillSinglePrice(sheet, rownum++, price);
		}
		sheet.autoSizeColumn(0);
	}

	private void fillSinglePrice(Sheet sheet, int rownum, GoldPrice price) {
		Row row = sheet.createRow(rownum);

		Cell date = row.createCell(0);
		date.setCellValue(toDate(price.getDate()));
		CellStyle dateStyle = createDateStyle(row.getSheet().getWorkbook());
		date.setCellStyle(dateStyle);

		Cell value = row.createCell(1);
		value.setCellValue(price.getPrice());
	}

	static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
