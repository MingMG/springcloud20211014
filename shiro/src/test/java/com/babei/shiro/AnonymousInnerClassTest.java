package com.babei.shiro;

/**
 * 匿名内部类测试
 * todo 1、不使用匿名内部类来实现抽象方法
 * todo 2、匿名内部类的基本实现
 * todo 3、在接口上使用匿名内部类
 * todo 4、Thread类的匿名内部类实现
 * todo 5、Runnable接口的匿名内部类实现
 * @Author ba'bei
 * @Date 2021/11/18
 **/

//抽象方法
abstract class Person {
    public abstract void eat();
}

abstract interface Person2{
    public void eat();
}
//todo 1、
/*class Child extends Person {
    public void eat() {
        System.out.println("eat something");
    }
}*/

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        // todo 1、
      /*  Person person = new Child();
        person.eat();*/

        // todo 2、
      /*  Person person = new Person() {
            public void eat() {
                System.out.println("eat something");
            }
        };
        person.eat();*/
        //todo 3、
   /*     Person2 person2 = new Person2() {
            @Override
            public void eat() {
                System.out.println("eat something");
            }
        };
        person2.eat();*/
        //todo 4、
      /*  Thread thread = new Thread(){
          public void run(){
              for (int i = 1; i <=5; i++) {
                  System.out.println(i+" ");
              }
          }
        };
        thread.start();*/
        //todo 5、

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=5; i++) {
                    System.out.println(i + " ");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
