package tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.activities.AddEditOutfitActivity;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.SelectOutfitListItemBinding;
import tr.edu.yildiz.virtualwardrobe.databinding.SelectWardrobeItemListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Outfit;
import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;

import static android.app.Activity.RESULT_OK;

public class SelectOutfitListAdapter extends RecyclerView.Adapter<SelectOutfitListAdapter.ViewHolder>{

    private List<Outfit> items;
    private Context context;
    private AppCompatActivity activity;
    private AppDatabase db;
    public  static class ViewHolder extends RecyclerView.ViewHolder{

        SelectOutfitListItemBinding binding;

        public ViewHolder(SelectOutfitListItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    public SelectOutfitListAdapter(List<Outfit> items, Context context,AppCompatActivity activity){
        this.items = items;
        this.context=context;
        this.activity=activity;
        db= Room.databaseBuilder(context,
                AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(SelectOutfitListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.binding.outfitNameTextView.setText(items.get(position).name);
        holder.binding.headImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).headItemId).photo));
        holder.binding.faceImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).faceItemId).photo));
        holder.binding.topImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).topItemId).photo));
        holder.binding.bottomImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).bottomItemId).photo));
        holder.binding.footwearImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).footwearItemId).photo));

        holder.binding.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected_outfit_id", items.get(position).id);
                activity.setResult(RESULT_OK, intent);
                activity.finish();
            }
        });

    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
