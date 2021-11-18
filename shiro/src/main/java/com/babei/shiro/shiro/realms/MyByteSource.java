package com.babei.shiro.shiro.realms;


import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * 自定义salt实现 实现序列化接口
 *@Author ba'bei
 *@Date 2021/10/22
 **/
public class MyByteSource extends SimpleByteSource implements Serializable {

        public MyByteSource(String string){
            super(string);
        }

}
