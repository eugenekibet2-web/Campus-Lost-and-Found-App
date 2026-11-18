package com.example.campuslostandfoundapp1;// KEEP YOUR PACKAGE NAME

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private List<Item> displayList;

    public ItemAdapter(Context context, List<Item> displayList) {
        this.context = context;
        this.displayList = displayList;
    }

    public void updateList(List<Item> newList) {
        this.displayList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = displayList.get(position);

        holder.tvName.setText(item.getName());
        holder.tvCategory.setText("Category: " + item.getCategory());
        holder.tvDescription.setText(item.getDescription());
        holder.tvLocationDate.setText("Location: " + item.getLocation() + "  |  Date: " + item.getDate());
        holder.tvContact.setText("Contact: " + item.getContact());
        holder.tvStatus.setText(item.getStatus().toUpperCase());

        if ("Lost".equalsIgnoreCase(item.getStatus())) {
            holder.tvStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.lost_red));
            holder.btnClaim.setVisibility(View.VISIBLE);
        } else if ("Found".equalsIgnoreCase(item.getStatus())) {
            holder.tvStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.found_green));
            holder.btnClaim.setVisibility(View.VISIBLE);
        } else {
            holder.tvStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.claimed_gray));
            holder.btnClaim.setVisibility(View.GONE);
        }

        // Optional Challenge: Confirm and mark item as Claimed/Returned
        holder.btnClaim.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirm Item Return")
                    .setMessage("Mark '" + item.getName() + "' as Claimed / Returned?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        item.setStatus("Claimed/Returned");
                        notifyItemChanged(holder.getAdapterPosition());
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStatus, tvCategory, tvDescription, tvLocationDate, tvContact;
        Button btnClaim;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvStatus = itemView.findViewById(R.id.tvItemStatus);
            tvCategory = itemView.findViewById(R.id.tvItemCategory);
            tvDescription = itemView.findViewById(R.id.tvItemDescription);
            tvLocationDate = itemView.findViewById(R.id.tvItemLocationDate);
            tvContact = itemView.findViewById(R.id.tvItemContact);
            btnClaim = itemView.findViewById(R.id.btnMarkClaimed);
        }
    }
}
