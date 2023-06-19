package com.example.diseaseprediction.helpers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diseaseprediction.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    int i = 0;
    private ArrayList<String> dataList;
    private Context context;

    public CustomAdapter(ArrayList<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        String[] colorHexCodes = context.getResources().getStringArray(R.array.color_hex_codes);
        String bgColor = colorHexCodes[i];
        i = (i+1) % 8;
        view.setBackgroundColor(Color.parseColor(bgColor));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] splitString = dataList.get(position).split(":");

        holder.headingTextView.setText(splitString[0]);
        holder.detailsTextView.setText(splitString[1]);
        holder.arrowImageView.setRotation(0);
        holder.detailsTextView.setVisibility(View.GONE);

        holder.headingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.detailsTextView.getVisibility() == View.GONE) {
                    // Show details
                    holder.detailsTextView.setVisibility(View.VISIBLE);
                    holder.arrowImageView.setRotation(180);
                } else {
                    // Hide details
                    holder.detailsTextView.setVisibility(View.GONE);
                    holder.arrowImageView.setRotation(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView headingTextView;
        private TextView detailsTextView;
        private ImageView arrowImageView;
        private LinearLayout headingLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headingTextView = itemView.findViewById(R.id.heading_text_view);
            detailsTextView = itemView.findViewById(R.id.details_text_view);
            arrowImageView = itemView.findViewById(R.id.arrow_image_view);
            headingLayout = itemView.findViewById(R.id.heading_layout);
        }
    }
}