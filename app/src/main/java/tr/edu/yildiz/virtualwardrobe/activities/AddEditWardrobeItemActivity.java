package tr.edu.yildiz.virtualwardrobe.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import java.io.IOException;

import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.ActivityAddEditWardrobeItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;

public class AddEditWardrobeItemActivity extends AppCompatActivity {

    private static final int PICK_FROM_GALLERY = 101;
    private Uri imageURI = null;
    private Bitmap imageBitmap = null;
    private String imageBitmapString = null;


    private AppDatabase db;


    private ActivityAddEditWardrobeItemBinding binding;
    private int selectedWardrobeItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedWardrobeItemId = getIntent().getIntExtra("selected_wardrobe_item", -1);
        binding = ActivityAddEditWardrobeItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();


        initializeComponents(selectedWardrobeItemId);


    }

    public void initializeComponents(int selected) {


        binding.attachImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });


        if (selected == -1) {

            binding.deleteImageButton.setVisibility(View.GONE);
            binding.updateButton.setVisibility(View.GONE);
            binding.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db.drawerDao().isDrawerExist(binding.drawerNameText.getText().toString()) != null) {
                        db.wardrobeItemDao().insertWardrobeItem(getInfo());

                        showAlert("New item added successfully");
                    } else {

                        showDrawerNotFoundAlert();

                    }

                }
            });

        } else {

            initializeInputs();
            binding.addButton.setVisibility(View.GONE);
            binding.updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (db.drawerDao().isDrawerExist(binding.drawerNameText.getText().toString()) != null) {
                        WardrobeItem item = getInfo();
                        item.id = selectedWardrobeItemId;
                        db.wardrobeItemDao().updateWardrobeItem(item);

                        showAlert("Item updated successfully");
                    } else {

                        showDrawerNotFoundAlert();
                    }
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

    public void pickImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Select a file to attach"), PICK_FROM_GALLERY);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            imageURI = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);
                imageBitmapString = BitmapOperations.BitMapToString(imageBitmap);
                binding.itemImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void showAlert(String message) {

        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();

                    }
                })

                .show();
    }

    public void showDrawerNotFoundAlert() {

        new AlertDialog.Builder(this)
                .setMessage("Drawer not found")
                .setPositiveButton(android.R.string.yes, null)
                .show();
    }

    public void initializeInputs() {
        WardrobeItem item = db.wardrobeItemDao().getWardrobeItem(selectedWardrobeItemId);

        binding.typeText.setText(item.type);
        binding.colorText.setText(item.color);
        binding.patternText.setText(item.pattern);
        binding.purchaseDateText.setText(item.purchaseDate);
        binding.priceText.setText(String.valueOf(item.price));
        binding.drawerNameText.setText(item.drawerName);

        imageBitmapString = item.photo;
        imageBitmap = BitmapOperations.StringToBitMap(imageBitmapString);
        binding.itemImageView.setImageBitmap(imageBitmap);


    }

    public WardrobeItem getInfo() {
        return new WardrobeItem(binding.typeText.getText().toString(),
                binding.colorText.getText().toString(),
                binding.patternText.getText().toString(),
                binding.purchaseDateText.getText().toString(),
                Float.parseFloat(binding.priceText.getText().toString()),
                imageBitmapString,
                binding.drawerNameText.getText().toString());


    }
    private void deleteAlert(){

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this drawer?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.wardrobeItemDao().deleteWardrobeItem(selectedWardrobeItemId);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}