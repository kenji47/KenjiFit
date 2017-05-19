package com.kenji1947.realmfit.scr_plan_catalog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.Plan;

import java.util.List;

import timber.log.Timber;

/**
 * Created by kenji1947 on 13.05.2017.
 */

public class ExpandablePlanAdapter
        extends ExpandableRecyclerAdapter<ParentGroup, Plan, ParentHolder, ChildHolder> {

    private LayoutInflater inflater;
    private List<ParentGroup> listPlanGroup;

    interface ClickListener {
        void onClick(int group_pos, int pos);
    }
    private ClickListener listener = new ClickListener() {
        @Override
        public void onClick(int group_pos, int child_pos) {
            Timber.d("onClick id: " + listPlanGroup.get(group_pos).getChildList().get(child_pos).get_id());
        }
    };


    public ExpandablePlanAdapter(Context context, @NonNull List<ParentGroup> list) {
        super(list);
        listPlanGroup = list;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<ParentGroup> list) {
        listPlanGroup.clear();
        listPlanGroup.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        for (ParentGroup group: listPlanGroup) {
            group.registerListener(this);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        for (ParentGroup group: listPlanGroup) {
            group.removeListener();
        }
    }

    int getPlanGroupIndex(ParentGroup planGroup) {
        return listPlanGroup.indexOf(planGroup);
    }

    @UiThread
    @NonNull
    @Override
    public ParentHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        recipeView = inflater.inflate(R.layout.plangroup_view, parentViewGroup, false);
        return new ParentHolder(recipeView);
    }

    @UiThread
    @NonNull
    @Override
    public ChildHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        ingredientView = inflater.inflate(R.layout.plan_view, childViewGroup, false);
        return new ChildHolder(ingredientView, listener);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull ParentHolder recipeViewHolder, int parentPosition, @NonNull ParentGroup recipe) {
        recipeViewHolder.bind(recipe);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ChildHolder ingredientViewHolder, int parentPosition, int childPosition, @NonNull Plan ingredient) {
        ingredientViewHolder.bind(ingredient);
    }

}
