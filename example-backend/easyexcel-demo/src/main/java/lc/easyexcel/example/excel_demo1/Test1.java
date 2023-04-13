package org.example.excel_demo1;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.compress.utils.Lists;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class Test1
{
    public static void main( String[] args )
    {
        writeExcel();
    }

    // 修改WriteSheet的代码如下
    public static void writeExcel() {
        String fileName = getPath();
        ExcelWriter excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();

        List<DemoData> demoDataList = data1();
        // 写sheet的时候注册相应的自定义合并单元格策略
        WriteSheet writeSheet = EasyExcel.writerSheet("模板1").head(DemoData.class)
                .registerWriteHandler(new CustomMergeStrategy(demoDataList.stream().map(DemoData::getString).collect(Collectors.toList()), 0))
                .build();
        excelWriter.write(demoDataList, writeSheet);
        excelWriter.finish();
    }
//    public static void writeExcel() {
//        // 写excel的路径，当前项目路径下
//        String fileName = getPath();
//        // 构建ExcelWriter
//        ExcelWriter excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();
//
//        // 构建sheet
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板1").head(DemoData.class).build();
//        // 写sheet
//        excelWriter.write(data1(), writeSheet);
//        excelWriter.finish();
//    }

    private static String getPath() {
        return System.getProperty("user.dir") + "/" + System.currentTimeMillis() + ".xlsx";
    }

    private static List<DemoData> data1() {
        List<DemoData> list = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + 1);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        for (int i = 0; i < 3; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + 2);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        for (int i = 0; i < 4; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + 3);
            data.setDate(new Date());
            data.setDoubleData(0.57);
            list.add(data);
        }
        return list;
    }

}
