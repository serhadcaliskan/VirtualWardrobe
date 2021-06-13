package tr.edu.yildiz.virtualwardrobe.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.entities.Outfit;

@Dao
public interface OutfitDao {

    @Query("SELECT * FROM Outfit")
    List<Outfit> getAllOutfits();

    @Insert
    void insertOutfit(Outfit o);

    @Query("SELECT * FROM Outfit Where id LIKE :id")
    Outfit findOutfit(int id);

    @Update
    void updateOutfit(Outfit o);

    @Query("DELETE FROM outfit WHERE id = :id")
    void deleteOutfit(int id);
}
