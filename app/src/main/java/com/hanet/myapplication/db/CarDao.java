package com.hanet.myapplication.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hanet.myapplication.model.Car;

import java.util.List;

@Dao
public interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Car> carList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCar(Car car);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(Car car);

    @Query("delete from Car where car_name=:name")
    int deleteCar(String name);

    @Query("SELECT * FROM Car")
    DataSource.Factory<Integer, Car> getCar();

    @Query("SELECT * FROM Car WHERE car_name LIKE:name")
    DataSource.Factory<Integer, Car> getCarByName(String name);
}
