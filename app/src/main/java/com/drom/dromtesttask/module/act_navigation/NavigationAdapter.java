package com.drom.dromtesttask.module.act_navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.view.RepositoryImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter
        extends RecyclerView.Adapter<NavigationAdapter.RepositoryHolder>
{
    private LayoutInflater layoutInflater;
    @NonNull private Context context;
    @NonNull private List<RepositoryViewModel> list;

    public NavigationAdapter( @NonNull final Context context, @NonNull final List<RepositoryViewModel> list ){
        this.list = list;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RepositoryHolder onCreateViewHolder( ViewGroup parent, int viewType ){
        View view = layoutInflater.inflate(R.layout.li_navigation_item, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull final RepositoryHolder holder, final int position ){
        RepositoryViewModel repository = list.get(position);

        holder.ivAvatar.loadImage(repository.getAvatarUrl());
        holder.txtName.append(repository.getName());

        String desc = repository.getDescription();
        if( desc == null ){
            desc = context.getString(R.string.txt_no_desc);
        }
        holder.txtDesc.append(desc);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    class RepositoryHolder
            extends RecyclerView.ViewHolder
    {
        @BindView( R.id.li_navigation_item_iv_avatar ) RepositoryImageView ivAvatar;
        @BindView( R.id.li_navigation_item_txt_name ) TextView txtName;
        @BindView( R.id.li_navigation_item_txt_desc ) TextView txtDesc;

        public RepositoryHolder( View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}