package com.babei.shiro;

/**
 * @Author ba'bei
 * @Date 2021/11/18
 **/
public class SimpleLambda {
    //1.前置条件，必须是函数式接口
    //2.参数的传递
    //3.代码编写方式
    //4.方法引用
    public static void main(String[] args) {
        //参数特性：
        //可以忽略 类型
        //参数只有一个情况，可以省略括号
    run((name -> String.format("name:",name)));

        /**
         * 代码编写特性
         * 1.单行表达式 省掉 return
         * 2，代码块
         */
        run(name -> "name:"+name);
        run((name -> {
           String name1= name;
           return "name:"+name1;
        }));

        //静态方法引用
        run(SimpleLambda::doFormat);
        //普通方法
        run(new SimpleLambda()::doFormat2);
    }

    public static String doFormat(String param2){
        return "name:"+param2;
    }
    public String doFormat2(String param2){
        return "name:"+param2;
    }

    public static void run (Format format){
        format.run("巴贝");

    }
    public interface  Format{
        String run(String name);
    }
}
