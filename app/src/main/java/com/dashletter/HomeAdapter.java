package com.dashletter;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private ArrayList<HomeInfo> arrayList;

    public HomeAdapter(ArrayList<HomeInfo> arrayList) {
        this.arrayList = arrayList;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_holder, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.title_view.setText(arrayList.get(position).getTitle());
        String thumbnail = arrayList.get(position).getThumbnail();
        Picasso.get()
                .load(thumbnail)
                .resize(1000, 500)
                .into(holder.thumbnail_view);
        holder.source_view.setText("Source: ".concat(arrayList.get(position).getSource()));
        holder.content_view1.setText(arrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        TextView title_view, source_view, content_view1;
        ImageView thumbnail_view;

        public HomeHolder(@NonNull final View itemView) {
            super(itemView);
            title_view = itemView.findViewById(R.id.title_view1);
            thumbnail_view = itemView.findViewById(R.id.thumbnail_view);
            source_view = itemView.findViewById(R.id.source_view);
            content_view1 = itemView.findViewById(R.id.content_view1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(itemView.getContext()).edit().putString("CONTENT_URL",arrayList.get(getAdapterPosition()).getUrl()).apply();
                    Intent intent = new Intent(itemView.getContext(), HomeDetailActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
