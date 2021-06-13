package tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.activities.AddEditDrawerActivity;
import tr.edu.yildiz.virtualwardrobe.activities.AddEditWardrobeItemActivity;
import tr.edu.yildiz.virtualwardrobe.databinding.SelectWardrobeItemListItemBinding;
import tr.edu.yildiz.virtualwardrobe.databinding.WardrobeItemListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;

import static android.app.Activity.RESULT_OK;


public class SelectWardrobeItemListAdapter extends RecyclerView.Adapter<SelectWardrobeItemListAdapter.ViewHolder>{

    private List<WardrobeItem> items;
    private Context context;
    private AppCompatActivity activity;

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        SelectWardrobeItemListItemBinding binding;

        public ViewHolder(SelectWardrobeItemListItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    public SelectWardrobeItemListAdapter(List<WardrobeItem> items, Context context,AppCompatActivity activity){
        this.items = items;
        this.context=context;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(SelectWardrobeItemListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.binding.imageView.setImageBitmap(BitmapOperations.StringToBitMap(items.get(position).photo));
        holder.binding.colorTextView.setText(items.get(position).color);
        holder.binding.paternTextField.setText(items.get(position).pattern);
        holder.binding.typeTextView.setText(items.get(position).type);

        holder.binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected_wardrobe_item_id", items.get(position).id);
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
