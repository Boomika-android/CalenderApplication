package com.example.calenderapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    ArrayList<Event> arrayList;

    public EventsAdapter(Context ignoredContext, ArrayList<Event> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_date.setText(arrayList.get(position).getDate());
        holder.txt_title.setText(arrayList.get(position).getTitle());
        holder.txt_description.setText(arrayList.get(position).getDescription());
    }

    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_date, txt_title, txt_description;

        public ViewHolder(View itemView){
            super(itemView);

            txt_date = itemView.findViewById(R.id.txt_date);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_description = itemView.findViewById(R.id.txt_description);
        }
    }
}
