package com.example.topsports;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_users")
public class ProfileUser {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String weight;
    private String height;
    private String age;
    private String favoriteSport;

    public ProfileUser( String weight, String height, String age, String favoriteSport) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.favoriteSport = favoriteSport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }
}
