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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    Category(String name, String description) {
        this.name = name;
        this.description = description;
        boolean found = false;
        for(int i = 0; i < Database.categories.size(); i++)
        {
            if(name.equalsIgnoreCase(Database.categories.get(i).getName()))
            {
                found = true;
                break;
            }
        }
        if(!found)
        {
            Database.categories.add(this);
            setID();
        }
    }

    public String toString() {
        return this.name + this.description; // Assuming Category has a 'name' field
        // Or return whatever field best represents the category
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
