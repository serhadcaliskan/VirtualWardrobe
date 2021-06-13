package tr.edu.yildiz.virtualwardrobe.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.entities.Drawer;

@Dao
public interface DrawerDao {

    @Query("SELECT * FROM Drawer")
    List<Drawer> getAllDrawers();

    @Insert
    void insertDrawer(Drawer d);

    @Update
    void updateDrawer(Drawer d);

    @Query("SELECT * From Drawer where name like:name")
    Drawer isDrawerExist(String name);

    @Query("DELETE FROM drawer WHERE id = :id")
    void deleteDrawer(int id);

}
