package sda.code.intermediate.part3.answers.xls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Compute {

	public static void main(String[] args) throws IOException {
		// F1 - mnożnik, G1 - wynik
		// wiersze: 1-1416, kolumny pośrednie: C, D
		File spreadsheet = Paths.get(System.getProperty("user.home"), "data", "spreadsheet.xls").toFile();
		try (NPOIFSFileSystem fs = new NPOIFSFileSystem(spreadsheet); Workbook wb = new HSSFWorkbook(fs)) {
			final Sheet sheet = wb.getSheetAt(0);
			final Row row = sheet.getRow(0);
			final Cell resultCell = row.getCell(6);
			System.out.println("Formuła G1: " + resultCell.getCellFormula());

			final FormulaEvaluator evaluatorBefore = wb.getCreationHelper().createFormulaEvaluator();
			CellValue cellValueBefore = evaluatorBefore.evaluate(resultCell);
			System.out.println("Wartość bez uzupełnienia F1: " + cellValueBefore.getNumberValue());

			final Cell factorCell = row.createCell(5);
			factorCell.setCellValue(5.0);
			
			CellValue cellValueCached = evaluatorBefore.evaluate(resultCell);
			System.out.println("Wartość po uzupełnieniu F1, z pamięci podręcznej: " + cellValueCached.getNumberValue());

			FormulaEvaluator evaluatorAfter = wb.getCreationHelper().createFormulaEvaluator();
			CellValue cellValueAfter = evaluatorAfter.evaluate(resultCell);
			System.out.println("Wartość po uzupełnieniu F1: " + cellValueAfter.getNumberValue());
		}
	}

}
