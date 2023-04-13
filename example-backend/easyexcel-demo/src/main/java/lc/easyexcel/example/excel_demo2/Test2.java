package org.example.excel_demo2;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;

import java.io.File;
import java.util.*;

public class Test2 {
    public static void main(String[] args) {

        Map<String, List<ExcelDto>> map = new HashMap<>(2);
        map.put("a", getListDtos());
        map.put("b", getListDtos());

        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(getPath());
//                .withTemplate("F:\\SourceCode\\easyexcel\\src\\main\\resources\\template2.xlsx");
        excelWriterBuilder.inMemory(Boolean.TRUE);
        ExcelWriter excelWriter = excelWriterBuilder.build();

        WriteSheet writeSheet = EasyExcel.writerSheet(0)
                .registerWriteHandler(CellMergeWriterHandler.getCellWriteHandler(CellMergeWriterHandler.CellMergeEnum.COLUMN, Arrays.asList(4)))
                .registerWriteHandler(CellMergeWriterHandler.getCellWriteHandler(CellMergeWriterHandler.CellMergeEnum.ROW, Arrays.asList(0,1,2)))
                .build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("a", getListDtos()), fillConfig, writeSheet);


        excelWriter.fill(new FillWrapper("b", getListDtos()), fillConfig, writeSheet);
        excelWriter.finish();

    }


    public static List<ExcelDto> getListDtos() {
        List<ExcelDto> list = new ArrayList<>();
        int t = 1000, i = 0;
        while (i<1) {
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            list.add(new ExcelDto("a",1,2));
            i ++;
        }
        return list;
    }

    private static String getPath() {
        return System.getProperty("user.dir") + "/" + System.currentTimeMillis() + ".xlsx";
    }
}
