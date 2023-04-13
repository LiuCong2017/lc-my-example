package org.example.excel_demo2;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Objects;

public class CellMergeWriterHandler {

    public static CellWriteHandler getCellWriteHandler(CellMergeEnum cellMergeEnum) {
        if(CellMergeEnum.ROW.equals(cellMergeEnum)) {
            return new RowMergeWriterHandler();
        }

        if(CellMergeEnum.COLUMN.equals(cellMergeEnum)) {
            return new ColumnMergeWriterHandler();
        }

        return null;
    }

    public static CellWriteHandler getCellWriteHandler(CellMergeEnum cellMergeEnum, List<Integer> cols) {
        if(CellMergeEnum.ROW.equals(cellMergeEnum)) {
            return new RowMergeWriterHandler(cols);
        }

        if(CellMergeEnum.COLUMN.equals(cellMergeEnum)) {
            return new ColumnMergeWriterHandler(cols);
        }

        return null;
    }

    public static class ColumnMergeWriterHandler extends AbstractMergeStrategy {

        private List<Integer> mergeCols;

        public ColumnMergeWriterHandler() {}

        public ColumnMergeWriterHandler(List<Integer> mergeCols) {
            this.mergeCols = mergeCols;
        }

        @Override
        protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
            if(CollectionUtils.isNotEmpty(this.mergeCols) && !this.mergeCols.contains(cell.getColumnIndex())) {
                return;
            }
            mergeCell(sheet, cell, CellMergeEnum.COLUMN);
        }

    }


    public static class RowMergeWriterHandler extends AbstractMergeStrategy {

        private List<Integer> mergeCols;

        public RowMergeWriterHandler() {}

        public RowMergeWriterHandler(List<Integer> mergeCols) {
            this.mergeCols = mergeCols;
        }

        @Override
        protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
            if(CollectionUtils.isNotEmpty(mergeCols) && !mergeCols.contains(cell.getColumnIndex())) {
                return;
            }
            mergeCell(sheet, cell, CellMergeEnum.ROW);
        }
    }


    private static void mergeCell(Sheet sheet, Cell cell, CellMergeEnum cellMergeEnum) {
        if (Objects.isNull(cell)) {
            return;
        }

        int rowIndex = cell.getRowIndex(), colIndex = cell.getColumnIndex();
        Row preRow = null;
        Cell preCell = null;

        if(CellMergeEnum.ROW.equals(cellMergeEnum)) {
            preRow = sheet.getRow(rowIndex - 1);
            preCell = preRow.getCell(colIndex);
        }

        if(CellMergeEnum.COLUMN.equals(cellMergeEnum)) {
            if(colIndex == 0) {
                return;
            }
            preRow = cell.getRow();
            preCell = preRow.getCell(colIndex - 1);
        }

        if (Objects.isNull(preRow)||Objects.isNull(preCell)) {
            return;
        }
        mergeRows(sheet, preCell, cell);
    }


    /**
     * 行单元格合并
     *
     * @param sheet
     * @param preCell
     * @param curCell
     */
    private static void mergeRows(Sheet sheet, Cell preCell, Cell curCell) {
        Object preCellValue = getCellValue(preCell), curCellValue = getCellValue(curCell);
        if(Objects.isNull(curCellValue)) {
            return;
        }

        if(!"".equals(preCellValue)) {
            if(!preCellValue.equals(curCellValue)) {
                return;
            }
            curCell.setCellType(CellType.BLANK);
            sheet.addMergedRegion(new CellRangeAddress(preCell.getRowIndex(), curCell.getRowIndex(), preCell.getColumnIndex(), curCell.getColumnIndex()));
            return;
        }

        List<CellRangeAddress> list = sheet.getMergedRegions();
        CellRangeAddress rangeAddress = list.stream().filter(e -> compareColAndRow(e, preCell)).findFirst().orElse(null);
        if(Objects.isNull(rangeAddress)) {
            if("".equals(curCellValue)) {
                curCell.setCellType(CellType.BLANK);
                sheet.addMergedRegion(new CellRangeAddress(preCell.getRowIndex(), curCell.getRowIndex(), preCell.getColumnIndex(), curCell.getColumnIndex()));
                return;
            }
            return;
        }
        int firstRow = rangeAddress.getFirstRow(), firstColumn = rangeAddress.getFirstColumn();
        String value = String.valueOf(getCellValue(sheet.getRow(firstRow).getCell(firstColumn)));
        if(!value.equals(curCellValue)) {
            return;
        }

        int lastRow = curCell.getRowIndex(), lastColumn = curCell.getColumnIndex();
        for (int i = 0; i < list.size(); i++) {
            if(rangeAddress.equals(list.get(i))) {
                sheet.removeMergedRegion(i);
                curCell.setCellType(CellType.BLANK);
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn));
                return;
            }
        }
    }

    private static boolean compareColAndRow(CellRangeAddress cellRangeAddress, Cell cell) {
        return cellRangeAddress.getFirstColumn()<=cell.getColumnIndex() && cellRangeAddress.getLastColumn()>=cell.getColumnIndex()
                &&cellRangeAddress.getFirstRow()<=cell.getRowIndex()&&cellRangeAddress.getLastRow()>=cell.getRowIndex();
    }


    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    protected static Object getCellValue(Cell cell) {
        if (Objects.isNull(cell)) {
            return "";
        }

        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            default:
                return "";
        }
    }

    public enum CellMergeEnum {
        ROW, COLUMN;
    }
}
