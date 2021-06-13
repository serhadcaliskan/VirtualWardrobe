package tr.edu.yildiz.virtualwardrobe.activities.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tr.edu.yildiz.virtualwardrobe.activities.AddEditDrawerActivity;
import tr.edu.yildiz.virtualwardrobe.activities.MenuActivity;
import tr.edu.yildiz.virtualwardrobe.activities.WardrobeItemListActivity;
import tr.edu.yildiz.virtualwardrobe.databinding.DrawerListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Drawer;

public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.ViewHolder>{

    private List<Drawer> items;

    private Context context;

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        DrawerListItemBinding binding;//Name of the test_list_item.xml in camel case + "Binding"

        public ViewHolder(DrawerListItemBinding b){
            super(b.getRoot());
            binding = b;
        }
    }

    public DrawerListAdapter(List<Drawer> items, Context context){
        this.items = items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(DrawerListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        //An example of how to use the bindings
        holder.binding.drawerNameTextView.setText(items.get(position).name);

        holder.binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( context, AddEditDrawerActivity.class);
                intent.putExtra("selected_drawer",items.get(position).id);

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
