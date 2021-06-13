package tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.activities.AddEditDrawerActivity;
import tr.edu.yildiz.virtualwardrobe.activities.AddEditWardrobeItemActivity;
import tr.edu.yildiz.virtualwardrobe.databinding.WardrobeItemListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.WardrobeItem;
import tr.edu.yildiz.virtualwardrobe.utilities.BitmapOperations;


public class WardrobeItemListAdapter extends RecyclerView.Adapter<WardrobeItemListAdapter.ViewHolder>{

    private List<WardrobeItem> items;
    private Context context;

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        WardrobeItemListItemBinding binding;

        public ViewHolder(WardrobeItemListItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    public WardrobeItemListAdapter(List<WardrobeItem> items, Context context){
        this.items = items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(WardrobeItemListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        //An example of how to use the bindings
        holder.binding.imageView.setImageBitmap(BitmapOperations.StringToBitMap(items.get(position).photo));
        holder.binding.colorTextView.setText(items.get(position).color);
        holder.binding.paternTextField.setText(items.get(position).pattern);
        holder.binding.typeTextView.setText(items.get(position).type);

        holder.binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( context, AddEditWardrobeItemActivity.class);
                intent.putExtra("selected_wardrobe_item",items.get(position).id);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
