package com.kenji1947.kenjifit.screen_ex_catalog;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.repo.db.ObjExercise;

import java.io.InputStream;
import java.util.List;

import timber.log.Timber;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ExListAdapter extends RecyclerView.Adapter<ExListAdapter.ExerciseListHolder>{
    List<ObjExercise> data;
    ItemClickListener clickListener;

    public ExListAdapter(List<ObjExercise> data, ItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    public void addExercises(List<ObjExercise> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
    //---------------------------------------------------------------------------------
    //TODO Какой класс должен объявлять интерфейс
    public interface ItemClickListener {
        void onClick(long ex_id);
    }

    public void setListener(@NonNull ItemClickListener listener) {
        this.clickListener = listener;
    }
    //--------------------------------------------------------------------------------------

    @Override
    public ExerciseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_dictionary, parent, false);
        return new ExerciseListHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseListHolder holder, int position) {
        // TODO: 27.04.2017
        holder.name.setText(data.get(position).getName().getRU());
        AssetManager assetManager = holder.itemView.getContext().getAssets();
        InputStream is = null;
        Bitmap bitmap = null;
        String icon = data.get(position).getExercise().getICON_URL();
        Timber.d("icon " + icon);
        Glide
                .with(holder.itemView.getContext())
                .load(Uri.parse("file:///android_asset/images/" + icon))
                .centerCrop()
                .crossFade()
                .into(holder.image);
    }

    //--------------
    @Override
    public int getItemCount() {
        return data.size();
    }
    //--------------
   public class ExerciseListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;

        public ExerciseListHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.nameTextView);
            this.image = (ImageView) itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Timber.d("onClick id: " + data.get(getAdapterPosition()).getExercise().getId());
            clickListener.onClick( data.get(getAdapterPosition()).getExercise().getId());
        }
    }
}
