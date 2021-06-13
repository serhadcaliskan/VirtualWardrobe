package tr.edu.yildiz.virtualwardrobe.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import tr.edu.yildiz.virtualwardrobe.entities.Drawer;
import tr.edu.yildiz.virtualwardrobe.entities.Event;
import tr.edu.yildiz.virtualwardrobe.entities.Outfit;
import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;

@Database(entities = {Drawer.class, Event.class, Outfit.class, WardrobeItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DrawerDao drawerDao();

    public abstract EventDao eventDao();

    public abstract OutfitDao outfitDao();

    public abstract WardrobeItemDao wardrobeItemDao();

}
