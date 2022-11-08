package com.lc.datasynch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kavin
 * @since 2022-11-08
 */
@Data
@TableName("table")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String gender;

    private LocalDate birthday;

    private Double salary;

    private String email;

    /**
     * Remark
     */
    private String remark;
}
