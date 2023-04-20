package com.example.appamands.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appamands.R;
import com.example.appamands.views.SlideItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class slide_adapter extends  RecyclerView.Adapter<slide_adapter.SlideViewHolder>{
    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

    public slide_adapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setImage(slideItems.get(position));
       if (position == slideItems.size()-2){
            position = 0;
        }

    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SlideItem slideItem){
            imageView.setImageResource(slideItem.getImage());
        }
    }
    private  Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slideItems.addAll(slideItems);
        }
    };
}
