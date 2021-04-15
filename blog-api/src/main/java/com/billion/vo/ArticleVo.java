package com.billion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Billion
 * @create 2021/04/09 19:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ArticleVo {
    private Integer id;
    private String title;
    private Integer commentCounts;
    private Integer viewCounts;
    private String summary;
    private AuthorVo author;
    private List<String> tags;
    private Date createDate;
}
