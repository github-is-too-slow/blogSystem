package com.billion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Billion
 * @create 2021/04/08 1:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ArticleTag {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer article;
    private Integer tag;
}
