package tr.edu.yildiz.virtualwardrobe.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class WardrobeItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="type")
    public String type;

    @ColumnInfo(name="color")
    public String color;

    @ColumnInfo(name="pattern")
    public String pattern;

    @ColumnInfo(name="purchase_date")
    public String purchaseDate;

    @ColumnInfo(name="price")
    public float price;

    @ColumnInfo(name="photo")
    public String photo;

    @NonNull
    @ColumnInfo(name="drawer_name")
    public String drawerName;

    @Ignore
    public WardrobeItem(String type, String color, String pattern, String purchaseDate, float price, String photo, String drawerName) {
        this.type = type;
        this.color = color;
        this.pattern = pattern;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.photo = photo;
        this.drawerName = drawerName;
    }


    public WardrobeItem(int id, String type, String color, String pattern, String purchaseDate, float price, String photo, String drawerName) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pattern = pattern;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.photo = photo;
        this.drawerName = drawerName;
    }


}
