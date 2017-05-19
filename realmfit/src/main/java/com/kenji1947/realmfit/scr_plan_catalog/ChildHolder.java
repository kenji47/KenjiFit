package com.kenji1947.realmfit.scr_plan_catalog;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bumptech.glide.Glide;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.Plan;

/**
 * Created by kenji1947 on 13.05.2017.
 */

public class ChildHolder extends ChildViewHolder implements View.OnClickListener{
    private TextView name;
    private TextView desc;
    private TextView image_url;
    private ImageView imageView;

    private ExpandablePlanAdapter.ClickListener listener;

    public ChildHolder(@NonNull View itemView, ExpandablePlanAdapter.ClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = (TextView) itemView.findViewById(R.id.planName);
        desc = (TextView) itemView.findViewById(R.id.planDescription);
        imageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        this.listener = listener;
    }

    public void bind(@NonNull Plan plan) {
        name.setText(plan.get_id());
        desc.setText(plan.getDescription().getRu());
        Glide
                .with(this.itemView.getContext())
                .load(Uri.parse("file:///android_asset/images/" + "ex_barbellcurl_icon.jpg"))
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(getParentAdapterPosition(), getChildAdapterPosition());
    }
}
