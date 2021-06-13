package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.OutfitListAdapter;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityOutfitListBinding;

public class OutfitListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ActivityOutfitListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.outfitListRecyclerView.setLayoutManager(layoutManager);
        initializeRecyclerView();
        initializeListeners();
    }
    private void initializeRecyclerView(){

        binding.outfitListRecyclerView.setAdapter(new OutfitListAdapter(db.outfitDao().getAllOutfits(),this));

    }
    private void initializeListeners(){

        binding.addOutfitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutfitListActivity.this,AddEditOutfitActivity.class);

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