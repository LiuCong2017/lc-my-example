package com.lc.datasynch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.datasynch.domain.entity.Table;
import com.lc.datasynch.mapper.TableMapper;
import com.lc.datasynch.service.TableService;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements TableService {

}
