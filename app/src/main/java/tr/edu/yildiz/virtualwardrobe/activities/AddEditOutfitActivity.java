package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityAddEditOutfitBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Outfit;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;

public class AddEditOutfitActivity extends AppCompatActivity {

    private ActivityAddEditOutfitBinding binding;

    private AppDatabase db;
    private int headId=-1;
    private int faceId=-1;
    private int topId=-1;
    private int bottomId=-1;
    private int footwearId=-1;


    private int selectedOutfit;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedOutfit=getIntent().getIntExtra("selected_outfit", -1);
        binding=ActivityAddEditOutfitBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        initializeListeners();
        isAnythingSelected(selectedOutfit);
    }

    private void initializeListeners(){
        binding.attachHeadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditOutfitActivity.this, SelectWardrobeItemActivity.class);
                startActivityForResult(i, 1);

            }
        });

        binding.attachFaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditOutfitActivity.this, SelectWardrobeItemActivity.class);
                startActivityForResult(i, 2);
            }
        });

        binding.attachTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditOutfitActivity.this, SelectWardrobeItemActivity.class);
                startActivityForResult(i, 3);

            }
        });

        binding.attachBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditOutfitActivity.this, SelectWardrobeItemActivity.class);
                startActivityForResult(i, 4);
            }
        });

        binding.attachFootwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEditOutfitActivity.this, SelectWardrobeItemActivity.class);
                startActivityForResult(i, 5);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                headId = data.getIntExtra("selected_wardrobe_item_id",-1);
                binding.headImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(headId).photo));
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                faceId = data.getIntExtra("selected_wardrobe_item_id",-1);
                binding.faceItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(faceId).photo));
            }
        }
        if (requestCode == 3) {
            if(resultCode == RESULT_OK) {
                topId = data.getIntExtra("selected_wardrobe_item_id",-1);
                db.wardrobeItemDao().getWardrobeItem(topId);
                binding.topItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(topId).photo));
            }
        }
        if (requestCode == 4) {
            if(resultCode == RESULT_OK) {
                bottomId = data.getIntExtra("selected_wardrobe_item_id",-1);
                binding.bottomItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(bottomId).photo));
            }
        }
        if (requestCode == 5) {
            if(resultCode == RESULT_OK) {
                footwearId = data.getIntExtra("selected_wardrobe_item_id",-1);
                binding.footwearItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(footwearId).photo));
            }
        }
    }

    public void isAnythingSelected(int selection){
        if(selection==-1){
            binding.deleteImageButton.setVisibility(View.GONE);
            binding.updateOutfit.setVisibility(View.GONE);
            binding.addOutfit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.outfitDao().insertOutfit(
                            new Outfit(binding.outfitName.getText().toString(),
                                    headId,faceId,topId,bottomId,footwearId));
                    showAlert("New outfit created successfully");
                }
            });

        }
        else{

            binding.addOutfit.setVisibility(View.GONE);
            Outfit outfit=db.outfitDao().findOutfit(selection);
            binding.outfitName.setText(outfit.name);

            headId=outfit.headItemId;
            faceId=outfit.faceItemId;
            topId=outfit.topItemId;
            bottomId=outfit.bottomItemId;
            footwearId=outfit.footwearItemId;


            binding.headImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(headId).photo));
            binding.faceItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(faceId).photo));
            binding.topItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(topId).photo));
            binding.bottomItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(bottomId).photo));
            binding.footwearItemImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(footwearId).photo));

            binding.updateOutfit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.outfitDao().updateOutfit(new Outfit(selectedOutfit,binding.outfitName.getText().toString(),
                            headId,faceId,topId,bottomId,footwearId));
                    showAlert("Outfit updated successfully");

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
                        db.outfitDao().deleteOutfit(selectedOutfit);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}