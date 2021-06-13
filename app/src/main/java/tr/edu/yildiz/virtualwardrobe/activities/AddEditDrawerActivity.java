package tr.edu.yildiz.virtualwardrobe.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityAddEditDrawerBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Drawer;

public class AddEditDrawerActivity extends AppCompatActivity {


    private ActivityAddEditDrawerBinding binding;

    private int selectedDrawerId;

    private AppDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedDrawerId = getIntent().getIntExtra("selected_drawer", -1);

        binding = ActivityAddEditDrawerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();
        initializeComponents(selectedDrawerId);

    }

    private void initializeComponents(int selected) {

        if (selected == -1) {
            binding.editDrawerButton.setVisibility(View.GONE);
            binding.deleteImageButton.setVisibility(View.GONE);
            binding.addDrawerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.drawerDao().insertDrawer(new Drawer(binding.drawerNameText.getText().toString()));

                    showAlert("New drawer added successfully.");
                }
            });


        } else {
            binding.addDrawerButton.setVisibility(View.GONE);

            binding.editDrawerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.drawerDao().updateDrawer(new Drawer(selectedDrawerId, binding.drawerNameText.getText().toString()));

                    showAlert("Updated successfully.");
                }
            });

            binding.deleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteAlert();
                }
            });
        }

    }

    public void showAlert(String message) {

        new AlertDialog.Builder(this)
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();

                    }
                })

                .show();
    }

    private void deleteAlert(){

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this drawer?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.drawerDao().deleteDrawer(selectedDrawerId);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}



