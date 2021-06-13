package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.WardrobeItemListAdapter;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityWardrobeItemListBinding;

public class WardrobeItemListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ActivityWardrobeItemListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWardrobeItemListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.wardrobeItemListRecyclerView.setLayoutManager(layoutManager);
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();


        initializeRecyclerView();
        initializeListeners();
    }
    private void initializeRecyclerView(){

        binding.wardrobeItemListRecyclerView.setAdapter(new WardrobeItemListAdapter(db.wardrobeItemDao().getAllWardrobeItems(),this));

    }
    private void initializeListeners(){
        binding.addWardrobeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WardrobeItemListActivity.this,AddEditWardrobeItemActivity.class);

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