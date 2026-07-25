package com.cognizant;
public class Car{
 private Engine engine;
 public Car(Engine engine){this.engine=engine;}
 public void drive(){
  System.out.println("Car is ready to drive.");
  engine.start();
 }
}