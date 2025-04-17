/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 *
 * @author lap store
 */

public class Category {
   private String name;
   private String description;
   Category(String name,String description){
     /*  if (Database.categories.size()!=0 && Database.categories.contains(name)) {
           System.out.println(name+"already exsits!!");
       }
       else{*/
           this.name=name;
           this.description = description;
       
   }
   public String getName(){
       return name;
   }
   public String getDescription(){
       return description;
   }
}
