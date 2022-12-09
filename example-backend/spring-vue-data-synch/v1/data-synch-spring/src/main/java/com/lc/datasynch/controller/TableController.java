package com.lc.datasynch.controller;


import com.lc.datasynch.domain.entity.Table;
import com.lc.datasynch.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kavin
 * @since 2022-11-08
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping("/getTable")
    public List<Table> getTable(){
        return tableService.list();
    }

    @PostMapping("/addTable")
    public boolean addTable(@RequestBody Table table){
        return tableService.save(table);
    }

    @PutMapping("/putTable")
    public boolean putTable(@RequestBody Table table){
        return tableService.updateById(table);
    }

    @DeleteMapping("/deleteTable")
    public boolean deleteTable(@RequestParam("id")String id){
        return tableService.removeById(id);
    }

}
