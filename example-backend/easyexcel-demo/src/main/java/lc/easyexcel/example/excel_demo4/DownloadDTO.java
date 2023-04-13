package org.example.excel_demo4;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DownloadDTO {
    @ExcelProperty(value = "物品种类", index = 0)
    private String category;
    @ExcelProperty(value = "水果名称", index = 1)
    private String fruit;
    @ExcelProperty(value = "水果颜色", index = 2)
    private String color;
    @ExcelProperty(value = "水果产期", index = 3)
    private Date produceDate;
}
