package com.jeneric.eventappfrontend.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.jeneric.eventappfrontend.R;

public class ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.img_placeholder_outdoor)
                .error(R.mipmap.img_placeholder)
                .into(imageView);
    }
}
