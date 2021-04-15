package com.billion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Billion
 * @create 2021/04/09 19:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AuthorVo {
    private Integer id;
    private String nickname;
    private String adavar;
}
