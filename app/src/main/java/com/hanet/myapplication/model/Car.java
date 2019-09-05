package com.hanet.myapplication.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "car_id")
    private int id;
    @ColumnInfo(name = "car_name")
    private String name;
    @ColumnInfo(name = "car_color")
    private String color;
    @ColumnInfo(name = "car_seat")
    private int seat;

    public Car() {
    }

    public Car(String name, String color, int seat) {
        this.name = name;
        this.color = color;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }


    public static DiffUtil.ItemCallback<Car> DIFF_CALLBACK = new  DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        Car car = (Car) obj;

        return car.id == this.id && car.name.equals(this.name);

    }
}
