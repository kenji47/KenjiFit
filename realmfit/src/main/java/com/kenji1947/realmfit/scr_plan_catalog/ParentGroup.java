package com.kenji1947.realmfit.scr_plan_catalog;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import java.util.List;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmList;
import timber.log.Timber;

/**
 * Created by kenji1947 on 15.05.2017.
 */

public class ParentGroup implements Parent<Plan> {
    PlanGoal planGoal;
    private RealmList<Plan> planList;
    private ExpandablePlanAdapter adapter;

    private OrderedRealmCollectionChangeListener<RealmList<Plan>> listener = new OrderedRealmCollectionChangeListener<RealmList<Plan>>() {
        @Override
        public void onChange(RealmList<Plan> collection, OrderedCollectionChangeSet changeSet) {

            int parent_pos = adapter.getPlanGroupIndex(ParentGroup.this);
            Timber.d("onChange group name:" + planGoal.getName().getRu() + " parent pos: " + parent_pos);

            if (changeSet == null) {
                adapter.notifyParentDataSetChanged(false);
                return;
            }
            // For deletions, the adapter has to be notified in reverse order.
            OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
            for (int i = deletions.length - 1; i >= 0; i--) {
                OrderedCollectionChangeSet.Range range = deletions[i];
                adapter.notifyChildRangeRemoved(parent_pos, range.startIndex, range.length);
            }

            OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
            for (OrderedCollectionChangeSet.Range range : insertions) {
                adapter.notifyChildRangeInserted(parent_pos, range.startIndex, range.length);
            }

            OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
            for (OrderedCollectionChangeSet.Range range : modifications) {
                adapter.notifyChildRangeChanged(parent_pos, range.startIndex, range.length);
            }
        }
    };
    public void registerListener(ExpandablePlanAdapter adapter) {
        this.adapter = adapter;
        this.planList.addChangeListener(listener);
    };
    public void removeListener() {
        planList.removeChangeListener(listener);
    }

    public ParentGroup(PlanGoal planGoal) {
        this.planGoal = planGoal;
        this.planList = planGoal.getPlans();
    }

    public String getName() {
        return planGoal.getName().getRu();
    }

    public String getIconPlan() {
        return planGoal.getIconName();
    }

    @Override
    public List<Plan> getChildList() {
        return planList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public Plan getPlan(int position) {
        return planList.get(position);
    }
}

