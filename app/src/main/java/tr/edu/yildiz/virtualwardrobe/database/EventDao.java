package tr.edu.yildiz.virtualwardrobe.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.entities.Event;

@Dao
public interface EventDao {

    @Query("SELECT * from Event")
    List<Event> getAllEvents();

    @Insert
    void insertEvent(Event e);

    @Update
    void updateEvent(Event e);

    @Query("SELECT * from Event where id like :id")
    Event getEvent(int id);

    @Query("DELETE FROM event WHERE id = :id")
    void deleteEvent(int id);
}
