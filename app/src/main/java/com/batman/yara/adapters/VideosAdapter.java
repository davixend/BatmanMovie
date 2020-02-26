package com.batman.yara.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batman.yara.R;
import com.batman.yara.db.model.ModelVideoDB;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    public interface onClickListener{
        void onClickItem(String idVideo, ImageView imageView, TextView yearName, TextView year);
    }

    private ArrayList<ModelVideoDB> mDataset;
    private Activity activity;
    private onClickListener onClickListener;

    public VideosAdapter(ArrayList<ModelVideoDB> mDataset, Activity activity, onClickListener onClickListener) {
        this.mDataset = mDataset;
        this.activity = activity;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public VideosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(mDataset.get(i).getTitle());
        viewHolder.year.setText(mDataset.get(i).getYear());
        if (!mDataset.get(i).getImdbID().isEmpty()) {
            Glide.with(activity.getApplicationContext())
                    .load(mDataset.get(i).getPoster())
//                    .apply(new RequestOptions().placeholder(R.drawable.comic))
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(viewHolder.image);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClickItem(mDataset.get(i).getImdbID(), viewHolder.image,
                        viewHolder.yearName, viewHolder.year);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, yearName;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_item_video_title);
            year = itemView.findViewById(R.id.text_view_item_video_year);
            yearName = itemView.findViewById(R.id.text_view_item_video_year_name);
            image = itemView.findViewById(R.id.image_view_item_video_cover);
        }
    }
}
