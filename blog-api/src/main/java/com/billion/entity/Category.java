package com.billion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Billion
 * @create 2021/04/07 16:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Category {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String img;
    private String description;
    @TableField(exist = false)
    private Integer counts = 0;
}
