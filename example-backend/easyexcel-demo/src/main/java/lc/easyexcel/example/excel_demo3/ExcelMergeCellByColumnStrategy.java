package org.example.excel_demo3;

import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class ExcelMergeCellByColumnStrategy {

    private int[]mergeColumnIndex;
    private int mergeRowIndex;
    private int standardColumnIndex;

    public ExcelMergeCellByColumnStrategy(int mergeRowIndex, int[] mergeColumnIndex, int standardColumnIndex) {
    }

    /**
     * 当前单元格向上合并
     *
     * @param writeSheetHolder
     * @param cell            当前单元格
     * @param curRowIndex      当前行
     * @param curColIndex      当前列
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {

        Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        Cell preCell = cell.getSheet().getRow(curRowIndex -1).getCell(curColIndex);
        Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() : preCell.getNumericCellValue();
        // 获取上一行第0列单元格和本行第0列单元格
        Cell preRowFirstCell = cell.getSheet().getRow(curRowIndex -1).getCell(standardColumnIndex);
        Object preRowFirstData = preRowFirstCell.getCellTypeEnum() == CellType.STRING ? preRowFirstCell.getStringCellValue() : preRowFirstCell.getNumericCellValue();
        Cell curRowFirstCell = cell.getSheet().getRow(curRowIndex).getCell(standardColumnIndex);
        Object curRowFirstData = curRowFirstCell.getCellTypeEnum() == CellType.STRING ? curRowFirstCell.getStringCellValue() : curRowFirstCell.getNumericCellValue();

        // 当前列不为标杆列，根据标杆列上下两个单元格值是否一致来合并
        // 当前列为标杆列并且上下两个单元格一致则合并
        if ((curColIndex==standardColumnIndex && preData.equals(curData)) || (curColIndex!=standardColumnIndex && preRowFirstData.equals(curRowFirstData) && preData.equals(curData))) {

            Sheet sheet = writeSheetHolder.getSheet();
            List mergeRegions = sheet.getMergedRegions();

            boolean isMerged =false;
            for (int i =0; i < mergeRegions.size() && !isMerged; i++) {
                CellRangeAddress cellRangeAddr = (CellRangeAddress) mergeRegions.get(i);
                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellRangeAddr.isInRange(curRowIndex -1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged =true;
                }
            }

            // 若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                CellRangeAddress cellRangeAddress =new CellRangeAddress(curRowIndex -1, curRowIndex, curColIndex, curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }

        }

    }

}
