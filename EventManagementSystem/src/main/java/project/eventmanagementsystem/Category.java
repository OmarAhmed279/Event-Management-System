/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.eventmanagementsystem;

/**
 * @author lap store
 */

public class Category {
    private String name;
    private String description;
    private int ID;

    Category(String name, String description) {
     /*  if (Database.categories.size()!=0 && Database.categories.contains(name)) {
           System.out.println(name+"already exsits!!");
       }
       else{*/
        this.name = name;
        this.description = description;
        Database.categories.add(this);
        setID();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setID() { //i changed all the set id in all classes due to a reason too long to explain so if you wanna know why ask me in call -omar

        this.ID = Database.categories.indexOf(this);
    }
}
