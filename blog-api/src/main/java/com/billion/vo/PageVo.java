package com.billion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Billion
 * @create 2021/04/09 19:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PageVo {
    private long current;
    private long pages;
    private long total;
    private List<ArticleVo> records;
}
