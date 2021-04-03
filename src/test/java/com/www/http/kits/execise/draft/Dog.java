package com.www.http.kits.execise.draft;

/**
 * 懒汉式
 */
public class Dog {
  private Dog(){

  }

  public static Dog dog = null ;

  public static Dog getDog(){
      if(dog==null){
          dog = new Dog();
      }
      return  dog ;
  }
}
