package com.example.appamands.views;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appamands.R;
import com.example.appamands.adapter.slide_adapter;

import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    private Handler slideHandler = new Handler();
    List<SlideItem> slideItem = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.carrosel);
        slideItem.add(new SlideItem(R.drawable.img2));
        slideItem.add(new SlideItem(R.drawable.img3));
        slideItem.add(new SlideItem(R.drawable.img2));
        slideItem.add(new SlideItem(R.drawable.img3));
        viewPager2.setAdapter(new slide_adapter(slideItem,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer(){
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunable);
                slideHandler.postDelayed(sliderRunable, 2000);
            }
        });

    }
    private Runnable sliderRunable = new Runnable() {
        @Override
        public void run()
        {
            int currentItem = viewPager2.getCurrentItem();
            int nextItem = currentItem + 1;
            if (nextItem >= slideItem.size()) {
                nextItem = 0;
            }
            viewPager2.setCurrentItem(nextItem);
            slideHandler.postDelayed(this, 5000); // Agendar a transição do próximo slide em 5 segundos

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunable,2000);
    }
}
