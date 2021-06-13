package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.SelectOutfitListAdapter;
import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.SelectWardrobeItemListAdapter;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivitySelectOutfitBinding;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivitySelectWardrobeItemBinding;

public class SelectOutfitActivity extends AppCompatActivity {

    private AppDatabase db;
    private ActivitySelectOutfitBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySelectOutfitBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.selectOutfitRecyclerView.setLayoutManager(layoutManager);
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        initializeRecyclerView();
    }
    private void initializeRecyclerView(){

        binding.selectOutfitRecyclerView.setAdapter(new SelectOutfitListAdapter(db.outfitDao().getAllOutfits(),this,this));

    }
}