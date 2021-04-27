package com.ebookfrenzyple.roomdemo;


import androidx.lifecycle.LiveData;
import  androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products WHERE name = :name")
    List<Product> findProduct(String name);

    @Query("DELETE FROM products WHERE name = :name")
    void deleteProduct(String name);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProducts();


}
