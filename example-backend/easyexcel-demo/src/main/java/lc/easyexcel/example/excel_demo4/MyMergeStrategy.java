package org.example.excel_demo4;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.excel_demo4.DownloadDTO;

import java.util.List;

public class MyMergeStrategy extends AbstractMergeStrategy {

    private List<DownloadDTO> fruitList;
    private List<Integer> fruitGroupCount;
    private Sheet sheet;

    public MyMergeStrategy(List<DownloadDTO> fruitList, List<Integer> fruitGroupCount) {
        this.fruitList = fruitList;
        this.fruitGroupCount = fruitGroupCount;
    }

    // 将该列全部合并成一个单元格
    private void mergeCommonColumn(Integer index) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(1, fruitList.size(), index, index);
        sheet.addMergedRegionUnsafe(cellRangeAddress);
    }

    // 按照分组将各种类别分别合并成一个单元格
    private void mergeGroupColumn(Integer index) {
        Integer rowCnt = 1;
        for (Integer count : fruitGroupCount) {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowCnt, rowCnt + count - 1, index, index);
            sheet.addMergedRegionUnsafe(cellRangeAddress);
            rowCnt += count;
        }
    }

    @Override
    protected void merge(org.apache.poi.ss.usermodel.Sheet sheet, Cell cell, Head head, Integer integer) {
        this.sheet = sheet;
        if (cell.getRowIndex() == 1) {
            switch (cell.getColumnIndex()) {
                case 0:
                    this.mergeCommonColumn(0);
                    break;
                case 1:
                    this.mergeGroupColumn(1);
                    break;
                case 2:
                    break;
                case 3:
                    this.mergeCommonColumn(3);
                    break;
                default:
                    break;
            }
        }
    }
}