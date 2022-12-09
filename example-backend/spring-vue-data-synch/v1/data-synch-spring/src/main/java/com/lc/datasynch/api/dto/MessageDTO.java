package com.lc.datasynch.api.dto;

import com.lc.datasynch.api.enums.OperationType;
import lombok.Data;

@Data
public class MessageDTO<E> {
    private OperationType operationType;
    private E data;
    private String key;
}
