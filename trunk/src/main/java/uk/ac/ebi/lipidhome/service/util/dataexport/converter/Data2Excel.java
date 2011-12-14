package uk.ac.ebi.lipidhome.service.util.dataexport.converter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import uk.ac.ebi.lipidhome.service.util.dataexport.DataContainer;

import java.util.List;

public class Data2Excel extends DataConverter{

    public Data2Excel(List<DataContainer> dataList) {
        super(dataList);
    }

    protected void fillHeaderRow(HSSFRow headerRow){
        if(dataList.size()>0){
            int colNum = 0;
            for (String key : dataList.get(0).getKeys()) {
                headerRow.createCell(colNum++).setCellValue(key);
            }
        }
    }


    public HSSFWorkbook getWorkbook(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("MS1 Search result");

		int rowNum = 0;
        fillHeaderRow(sheet.createRow(rowNum++));
        for (DataContainer item : dataList) {
            HSSFRow row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String key : item.getKeys()) {
                row.createCell(colNum++).setCellValue(item.getValue(key));
            }
        }
		return workbook;
	}

    @Override
    String convert() {
        return null;
    }
}
