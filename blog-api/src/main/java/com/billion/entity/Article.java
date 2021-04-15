package com.billion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Billion
 * @create 2021/04/07 17:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Article {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer author;
    private String title;
    private String summary;
    private String body;
    private String category;
    private Date createDate;
    private Integer viewCounts;
    private Integer commentCounts;

    @TableField(exist = false)
    private String[] tags;
}
