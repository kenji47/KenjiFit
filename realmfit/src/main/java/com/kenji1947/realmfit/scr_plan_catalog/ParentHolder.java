package com.kenji1947.realmfit.scr_plan_catalog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bumptech.glide.Glide;
import com.kenji1947.realmfit.R;

/**
 * Created by kenji1947 on 13.05.2017.
 */

public class ParentHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageView mArrowExpandImageView;
    private TextView name;
    private ImageView imageView;

    public ParentHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.recipe_textview);
        imageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        mArrowExpandImageView = (ImageView) itemView.findViewById(R.id.arrow_expand_imageview);
    }

    public void bind(@NonNull ParentGroup planGroup) {
        name.setText(planGroup.getName());
        Context context = this.itemView.getContext();
        Glide
                .with(this.itemView.getContext())
                .load(context.getResources().getIdentifier(
                        planGroup.getIconPlan(),
                        "drawable",
                        context.getPackageName()))
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                mArrowExpandImageView.setRotation(ROTATED_POSITION);
            } else {
                mArrowExpandImageView.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            mArrowExpandImageView.startAnimation(rotateAnimation);
        }
    }

}
