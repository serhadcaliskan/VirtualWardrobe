package tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;


import tr.edu.yildiz.virtualwardrobe.activities.AddEditOutfitActivity;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.OutfitListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Outfit;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;


public class OutfitListAdapter extends RecyclerView.Adapter<OutfitListAdapter.ViewHolder>{

    private List<Outfit> items;

    private AppDatabase db;
    private Context context;

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        OutfitListItemBinding binding;

        public ViewHolder(OutfitListItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    public OutfitListAdapter(List<Outfit> items, Context context){
        this.items = items;
        this.context=context;
        db = Room.databaseBuilder(context,
                AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(OutfitListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        //An example of how to use the bindings
        holder.binding.outfitNameTextView.setText(items.get(position).name);
        holder.binding.headImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).headItemId).photo));
        holder.binding.faceImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).faceItemId).photo));
        holder.binding.topImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).topItemId).photo));
        holder.binding.bottomImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).bottomItemId).photo));
        holder.binding.footwearImageView.setImageBitmap(BitmapOperations.StringToBitMap(db.wardrobeItemDao().getWardrobeItem(items.get(position).footwearItemId).photo));

        holder.binding.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( context, AddEditOutfitActivity.class);
                intent.putExtra("selected_outfit",items.get(position).id);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
