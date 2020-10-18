package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayuiPage {
    private int code;
    private String msg;
    private long count;
    private List<?> data;
}
