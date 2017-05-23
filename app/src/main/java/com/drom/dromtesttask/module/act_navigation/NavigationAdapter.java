package com.drom.dromtesttask.module.act_navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.view.CircleTransform;
import com.drom.dromtesttask.model.Owner;
import com.drom.dromtesttask.model.RepositoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.RepositoryHolder> {

    private List<RepositoryItem> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public NavigationAdapter(Context context, List<RepositoryItem> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.li_navigation_item, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        RepositoryItem repository = list.get(position);
        Owner owner = repository.getOwner();

        setAvatar(holder, owner);
        setName(holder, repository);
        setDesc(holder, repository);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RepositoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.li_navigation_item_iv_avatar) ImageView ivAvataro;
        @BindView(R.id.li_navigation_item_txt_name) TextView txtName;
        @BindView(R.id.li_navigation_item_txt_desc) TextView txtDesc;

        public RepositoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // region - Methods -

    private void setAvatar(RepositoryHolder holder, Owner owner) {
        String avatarUrl = owner.getAvatarUrl();
        Picasso.with(context)
                .load(avatarUrl)
                .transform(new CircleTransform())
                .into(holder.ivAvataro);
    }

    private void setName(RepositoryHolder holder, RepositoryItem repository) {
        String name = repository.getFullName();
        holder.txtName.append(name);
    }

    private void setDesc(RepositoryHolder holder, RepositoryItem repository) {
        String desc = repository.getDescription();

        if (desc == null) {
            desc = context.getString(R.string.txt_no_desc);
            holder.txtDesc.append(desc);
        } else {
            holder.txtDesc.append(desc);
        }
    }

    // endregion
}