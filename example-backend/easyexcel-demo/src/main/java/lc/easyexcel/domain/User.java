package lc.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty()
    private String name;
    private Integer age;
    private LocalDate birth;
}
