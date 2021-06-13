package tr.edu.yildiz.virtualwardrobe.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Event {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="type")
    public String type;

    @ColumnInfo(name="location")
    public String location;

    @ColumnInfo(name="date")
    public String date;

    @ColumnInfo(name="outfit_id")
    public int outfitId;

    @Ignore
    public Event(String name, String type, String location, String date, int outfitId) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.date = date;
        this.outfitId = outfitId;
    }

    public Event(int id, String name, String type, String location, String date, int outfitId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.date = date;
        this.outfitId = outfitId;
    }
}
