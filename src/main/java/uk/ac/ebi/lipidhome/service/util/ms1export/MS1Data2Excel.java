package uk.ac.ebi.lipidhome.service.util.ms1export;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class MS1Data2Excel extends MS1DataConverter{

    public MS1Data2Excel(List<MS1DataContainer> dataList) {
        super(dataList);
    }

    protected void fillHeaderRow(HSSFRow headerRow){
        int colNum = 0;
        headerRow.createCell(colNum++).setCellValue("itemID");
        headerRow.createCell(colNum++).setCellValue("name");
        headerRow.createCell(colNum++).setCellValue("code");
        headerRow.createCell(colNum++).setCellValue("type");
        headerRow.createCell(colNum++).setCellValue("mass");
        headerRow.createCell(colNum++).setCellValue("identified");
        headerRow.createCell(colNum++).setCellValue("faCarbons");
        headerRow.createCell(colNum++).setCellValue("faDoubleBonds");
        headerRow.createCell(colNum++).setCellValue("resMass");
        headerRow.createCell(colNum++).setCellValue("delta");
    }


    public HSSFWorkbook getWorkbook(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("MS1 Search result");

		int rowNum = 0;
        fillHeaderRow(sheet.createRow(rowNum++));
        for (MS1DataContainer item : dataList) {
            HSSFRow row = sheet.createRow(rowNum++);
            int colNum = 0;
            row.createCell(colNum++).setCellValue(item.getItemId());
            row.createCell(colNum++).setCellValue(item.getName());
            row.createCell(colNum++).setCellValue(item.getCode());
            row.createCell(colNum++).setCellValue(item.getType().toString());
            row.createCell(colNum++).setCellValue(item.getMass());
            row.createCell(colNum++).setCellValue(item.getIdentified());
            row.createCell(colNum++).setCellValue(item.getFACarbons());
            row.createCell(colNum++).setCellValue(item.getFADoubleBonds());
            row.createCell(colNum++).setCellValue(item.getResMass());
            row.createCell(colNum++).setCellValue(item.getDelta());
        }
		return workbook;
	}

    @Override
    String convert() {
        return null;
    }
}
