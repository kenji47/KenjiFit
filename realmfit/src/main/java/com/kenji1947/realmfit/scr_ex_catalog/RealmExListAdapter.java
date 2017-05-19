package com.kenji1947.realmfit.scr_ex_catalog;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;

import java.io.InputStream;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import timber.log.Timber;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class RealmExListAdapter extends RealmRecyclerViewAdapter<Exercise, RealmExListAdapter.MyHolder> {

    private ItemClickListener clickListener;
    public interface ItemClickListener {
        void onClick(String ex_id);
    }

    public RealmExListAdapter(@Nullable OrderedRealmCollection<Exercise> data, ItemClickListener clickListener) {
        super(data, true);
        this.clickListener = clickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_dictionary, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Exercise exercise = getItem(position);

        holder.name.setText(exercise.getName().getRu());
        AssetManager assetManager = holder.itemView.getContext().getAssets();
        InputStream is = null;
        Bitmap bitmap = null;
        String icon = exercise.getIconUrl();
        Timber.d("icon " + icon);
        Glide
                .with(holder.itemView.getContext())
                .load(Uri.parse("file:///android_asset/images/" + icon))
                .centerCrop()
                .crossFade()
                .into(holder.image);
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.nameTextView);
            this.image = (ImageView) itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Timber.d("onClick server_id: " + getItem(getAdapterPosition()).getServerId());
            clickListener.onClick(getItem(getAdapterPosition()).getServerId());
        }
    }
}
