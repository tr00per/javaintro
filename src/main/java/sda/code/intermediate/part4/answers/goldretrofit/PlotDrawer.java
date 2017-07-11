package sda.code.intermediate.part4.answers.goldretrofit;

import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class PlotDrawer {

    public static void draw(Sheet dataSheet, int categoriesColumn, int valuesColumn) {
        Workbook wb = dataSheet.getWorkbook();
        Sheet plotSheet = wb.createSheet(Messages.SHEET_NAME_PLOT);

        Drawing drawing = plotSheet.createDrawingPatriarch();
        Chart chart = drawing.createChart(drawing.createAnchor(0, 0, 0, 0, 1, 1, 10, 20));

        LineChartData data = chart.getChartDataFactory().createLineChartData();
        ChartDataSource<String> xs = DataSources.fromStringCellRange(dataSheet,
                new CellRangeAddress(0, GoldService.LONGEST_PERIOD_IN_DAYS, categoriesColumn, categoriesColumn));
        ChartDataSource<Number> ys = DataSources.fromNumericCellRange(dataSheet,
                new CellRangeAddress(0, GoldService.LONGEST_PERIOD_IN_DAYS, valuesColumn, valuesColumn));
        data.addSeries(xs, ys);

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        chart.plot(data, bottomAxis, leftAxis);
    }

}
