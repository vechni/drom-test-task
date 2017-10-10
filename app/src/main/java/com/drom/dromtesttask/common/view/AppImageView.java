package com.drom.dromtesttask.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.drom.dromtesttask.R;
import com.squareup.picasso.Picasso;

public class AppImageView
        extends AppCompatImageView
{
    public AppImageView( @NonNull final Context context ){
        super(context);
    }

    public AppImageView( @NonNull final Context context, @Nullable AttributeSet attrs ){
        super(context, attrs);
    }

    public AppImageView( @NonNull final Context context, @Nullable AttributeSet attrs, int defStyleAttr ){
        super(context, attrs, defStyleAttr);
    }

    public void loadImage( @NonNull final String url ){
        Picasso.with(getContext())
                .load(url)
                .resize(60, 60)
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .transform(new CircleTransform())
                .into(this);
    }
}
