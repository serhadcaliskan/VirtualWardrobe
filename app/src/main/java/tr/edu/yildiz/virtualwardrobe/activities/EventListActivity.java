package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.EventListAdapter;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityEventListBinding;

public class EventListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ActivityEventListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.eventListRecyclerView.setLayoutManager(layoutManager);
        initializeRecyclerView();
        initializeListeners();
    }

    private void initializeRecyclerView(){


        binding.eventListRecyclerView.setAdapter(new EventListAdapter(db.eventDao().getAllEvents(),this));

    }

    private void initializeListeners() {

        binding.addEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EventListActivity.this,AddEditEventActivity.class);

                startActivity(intent);

            }


        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeRecyclerView();
    }
}