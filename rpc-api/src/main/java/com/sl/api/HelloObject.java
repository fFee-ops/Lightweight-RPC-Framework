package com.sl.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by yazai
 * Date: 下午2:07 2021/7/4
 *
 * 测试的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloObject implements Serializable {
    private  Integer id;
    //传递的讯息
    private  String message;
}
