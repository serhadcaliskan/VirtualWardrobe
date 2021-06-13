package tr.edu.yildiz.virtualwardrobe.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;

@Dao
public interface WardrobeItemDao {

    @Query("SELECT * FROM WardrobeItem")
    List<WardrobeItem> getAllWardrobeItems();

    @Query("SELECT * FROM WardrobeItem Where id LIKE :id")
    WardrobeItem getWardrobeItem(int id);

    @Insert
    void insertWardrobeItem(WardrobeItem item);

    @Update
    void updateWardrobeItem(WardrobeItem item);

    @Query("DELETE FROM wardrobeitem WHERE id = :id")
    void deleteWardrobeItem(int id);
}
