package tr.edu.yildiz.virtualwardrobe.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Drawer {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="name")
    public String name;


    @Ignore
    public Drawer(String name) {
        this.name = name;
    }


    public Drawer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
