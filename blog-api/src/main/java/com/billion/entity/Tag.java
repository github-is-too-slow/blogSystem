package com.billion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Tag {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String img;
}
