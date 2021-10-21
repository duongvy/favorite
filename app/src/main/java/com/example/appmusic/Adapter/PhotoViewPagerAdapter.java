package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Photo;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls;
    private List<Photo> mListPhoto;
    public PhotoViewPagerAdapter(Context context, String[] imageUrls){
        this.context = context;
        this.imageUrls = imageUrls;
    }
    public PhotoViewPagerAdapter(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load(imageUrls[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getCount() {

        if (mListPhoto != null)
            return mListPhoto.size();
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
