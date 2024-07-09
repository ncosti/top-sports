package com.example.topsports;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SportivDao {
    @Insert
    void insert(Sportiv sportiv);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProfileUser user);
    @Query("SELECT * FROM sportivi")
    List<Sportiv> getAllSportivi();
    @Query("select * from sportivi where email=:email and parola=:parola ")
     int findUsers(String email, String parola);
//    @Query("SELECT sport, COUNT(*) as count FROM sportivi GROUP BY sport")
//    List<SportCount> getSportCounts();
}
