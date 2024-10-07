package com.jeneric.eventappfrontend.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.EventItemBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.RecyclerViewInterface;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context context;
    private List<EventModel> eventList;
    private final RecyclerViewInterface recyclerViewInterface;

    public EventAdapter(Context context, List<EventModel> eventList, RecyclerViewInterface recyclerViewInterface) {
        this.eventList = eventList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.event_item,
                parent,
                false);
        return new EventViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        holder.eventItemBinding.setEvent(event);
        holder.eventItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        private EventItemBinding eventItemBinding;

        public EventViewHolder(EventItemBinding eventItemBinding, RecyclerViewInterface recyclerViewInterface) {
            super(eventItemBinding.getRoot());
            this.eventItemBinding = eventItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void setFilteredList(List<EventModel> filteredList) {
        eventList = filteredList;
        notifyDataSetChanged();
    }
}
