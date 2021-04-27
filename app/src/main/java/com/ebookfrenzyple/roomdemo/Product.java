package com.ebookfrenzyple.roomdemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    private  int id;
    private  String name;
    private  int quantity;

    public  Product(String name, int quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;

    }

    public int getId(){
        return  this.id;
    }
    public String getName(){
        return this.name;

    }
    public int getQuantity(){
        return  this.quantity;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}














