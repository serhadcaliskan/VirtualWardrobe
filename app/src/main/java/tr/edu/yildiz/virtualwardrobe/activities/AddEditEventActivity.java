package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityAddEditEventBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Event;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;

public class AddEditEventActivity extends AppCompatActivity {

    private ActivityAddEditEventBinding binding;
    private AppDatabase db;
    private int selectedEvent;
    private int selectedOutfit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityAddEditEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        selectedEvent=getIntent().getIntExtra("selected_event", -1);

        initializeListeners(selectedEvent);

    }


    private void initializeListeners(int selected){

        binding.attachOutfitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditEventActivity.this, SelectOutfitActivity.class);
                startActivityForResult(i, 1);
            }
        });

        if(selected==-1){

            binding.updateEventButton.setVisibility(View.GONE);
            binding.deleteImageButton.setVisibility(View.GONE);
            binding.addEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.eventDao().insertEvent(new Event(binding.eventNameText.getText().toString(),
                            binding.eventTypeText.getText().toString(),binding.eventLocationText.getText().toString(),
                            binding.eventDateText.getText().toString(),selectedOutfit));

                    showAlert("Event added successfully");
                }
            });

        }
        else{
            Event event=db.eventDao().getEvent(selectedEvent);
            binding.eventDateText.setText(event.date);
            binding.eventLocationText.setText(event.location);
            binding.eventNameText.setText(event.name);
            binding.eventTypeText.setText(event.type);
            selectedOutfit=event.outfitId;
            binding.attachStatusTextView.setText(db.outfitDao().findOutfit(selectedOutfit).name);




            binding.addEventButton.setVisibility(View.GONE);

            binding.updateEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.eventDao().updateEvent(new Event(selectedEvent,binding.eventNameText.getText().toString(),
                            binding.eventTypeText.getText().toString(),binding.eventLocationText.getText().toString(),
                            binding.eventDateText.getText().toString(),selectedOutfit));

                    showAlert("Event Updated Successfully");
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                selectedOutfit = data.getIntExtra("selected_outfit_id", -1);
                binding.attachStatusTextView.setText(db.outfitDao().findOutfit(selectedOutfit).name);
            }
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
                        db.eventDao().deleteEvent(selectedEvent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}