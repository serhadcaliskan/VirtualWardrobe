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

import tr.edu.yildiz.virtualwardrobe.activities.AddEditEventActivity;
import tr.edu.yildiz.virtualwardrobe.database.AppDatabase;
import tr.edu.yildiz.virtualwardrobe.databinding.EventListItemBinding;
import tr.edu.yildiz.virtualwardrobe.entities.Event;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder>{

    private List<Event> items;
    private AppDatabase db;
    private Context context;
    public  static class ViewHolder extends RecyclerView.ViewHolder{

        EventListItemBinding binding;

        public ViewHolder(EventListItemBinding b){
            super(b.getRoot());
            binding = b;

        }
    }

    public EventListAdapter(List<Event> items,Context context){
        this.items = items;
        this.context=context;
        db = Room.databaseBuilder(context,
                AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new ViewHolder(EventListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        //An example of how to use the bindings
        holder.binding.eventNameTextView.setText(items.get(position).name);
        holder.binding.eventDateTextView.setText(items.get(position).date);
        holder.binding.eventLocationTextView.setText(items.get(position).location);
        holder.binding.eventTypeTextView.setText(items.get(position).type);
        holder.binding.outfitTextView.setText(db.outfitDao().findOutfit(items.get(position).id).name);
        holder.binding.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( context, AddEditEventActivity.class);
                intent.putExtra("selected_event",items.get(position).id);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
