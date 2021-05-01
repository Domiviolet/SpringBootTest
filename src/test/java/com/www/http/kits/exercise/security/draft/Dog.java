package com.www.http.kits.exercise.security.draft;

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
