package com.drom.dromtesttask.module.act_navigation.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.view.AppImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter
        extends RecyclerView.Adapter<NavigationAdapter.RepositoryHolder>
{
    @NonNull private List<RepositoryViewModel> repositories = new ArrayList<>();

    public NavigationAdapter(){
    }

    public void setItems( @NonNull final List<RepositoryViewModel> repositories ){
        this.repositories = repositories;
    }

    public void addItems( @NonNull final List<RepositoryViewModel> repositories ){
        this.repositories.addAll(repositories);
    }

    @Override
    public RepositoryHolder onCreateViewHolder( ViewGroup parent, int viewType ){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_navigation_item, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull final RepositoryHolder holder, final int position ){
        final RepositoryViewModel repository = repositories.get(position);

        holder.ivAvatar.loadImage(repository.getAvatarUrl());
        holder.txtName.setText(repository.getName());
        holder.txtDesc.setText(repository.getDescription());
    }

    @Override
    public int getItemCount(){
        return repositories.size();
    }

    class RepositoryHolder
            extends RecyclerView.ViewHolder
    {
        @BindView( R.id.li_navigation_item_iv_avatar ) protected AppImageView ivAvatar;
        @BindView( R.id.li_navigation_item_txt_name ) protected TextView txtName;
        @BindView( R.id.li_navigation_item_txt_desc ) protected TextView txtDesc;

        RepositoryHolder( @NonNull final View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}