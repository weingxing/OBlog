package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章数据传输封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentInfo {
    private int cid;
    private String meta;
    private String title;
    private String created;
    private String modified;
    private String text;
    private int commentNum;
    private String status;
    private String author;
}
