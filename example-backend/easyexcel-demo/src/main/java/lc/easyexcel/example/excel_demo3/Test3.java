package org.example.excel_demo3;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.WriteHandler;
import org.example.excel_demo2.ExcelDto;

import java.util.Collection;

import static java.lang.System.out;

public class Test3 {

    public static void main(String[] args) {

        //设置0，1，4为需要合并的列
        int[] mergeColumnIndex = {0, 1, 4};
        // 从第一行开始合并
        int mergeRowIndex =1;
        // 设置第0列为标杆列
        int standardColumnIndex =0;

        Collection<?> collect = null;
        EasyExcel.write(out, ExcelDto.class)
        // 设置合并单元格策略
        .registerWriteHandler((WriteHandler) new ExcelMergeCellByColumnStrategy(mergeRowIndex, mergeColumnIndex, standardColumnIndex))
        .sheet("导出信息")
        .doWrite(collect);
    }
}
