package com.cognizant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App{
 public static void main(String[] args){
  ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
  HelloWorld h=ctx.getBean("helloBean",HelloWorld.class);
  h.display();
 }
}