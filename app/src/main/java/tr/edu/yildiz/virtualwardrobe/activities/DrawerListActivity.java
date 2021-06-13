package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters.DrawerListAdapter;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityDrawerListBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Drawer;

public class DrawerListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ActivityDrawerListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.drawerListRecyclerView.setLayoutManager(layoutManager);

        initializeRecyclerView();
        initializeListeners();

    }

    private void initializeRecyclerView() {

        binding.drawerListRecyclerView.setAdapter(new DrawerListAdapter(db.drawerDao().getAllDrawers(),this));

    }

    private void initializeListeners() {

        binding.addDrawer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DrawerListActivity.this,AddEditDrawerActivity.class);

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