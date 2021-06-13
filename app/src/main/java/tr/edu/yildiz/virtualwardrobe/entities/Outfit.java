package tr.edu.yildiz.virtualwardrobe.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Outfit {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="head_item_id")
    public int headItemId;

    @ColumnInfo(name="face_item_id")
    public int faceItemId;

    @ColumnInfo(name="top_item_id")
    public int topItemId;

    @ColumnInfo(name="bottom_item_id")
    public int bottomItemId;

    @ColumnInfo(name="footwear_item_id")
    public int footwearItemId;

    public Outfit(int id, String name, int headItemId, int faceItemId, int topItemId, int bottomItemId, int footwearItemId) {
        this.id = id;
        this.name = name;
        this.headItemId = headItemId;
        this.faceItemId = faceItemId;
        this.topItemId = topItemId;
        this.bottomItemId = bottomItemId;
        this.footwearItemId = footwearItemId;
    }

    @Ignore
    public Outfit(String name, int headItemId, int faceItemId, int topItemId, int bottomItemId, int footwearItemId) {
        this.name = name;
        this.headItemId = headItemId;
        this.faceItemId = faceItemId;
        this.topItemId = topItemId;
        this.bottomItemId = bottomItemId;
        this.footwearItemId = footwearItemId;
    }
}
