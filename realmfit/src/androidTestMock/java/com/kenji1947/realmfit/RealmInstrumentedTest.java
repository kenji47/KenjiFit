package com.kenji1947.realmfit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.scr_ex_catalog_presenter.ExListPresenter;
import com.kenji1947.realmfit.scr_ex_catalog_presenter.ExListView$$State;

import io.realm.OrderedCollectionChangeSet;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by kenji1947 on 15.05.2017.
 */

public class RealmInstrumentedTest {
    private Realm realm;
    PlanGoal[] plan_goal_array;

    @Mock
    ExListView$$State exListView$$State;

    @Captor
    ArgumentCaptor<List<OrderedCollectionChangeSet>> argumentCaptor;

    ExListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RealmConfiguration config =
                new RealmConfiguration.Builder().inMemory().name("test-realm").build();
        realm = Realm.getInstance(config);
        presenter = new ExListPresenter(realm);
        presenter.setViewState(exListView$$State);
        fillDb();

    }

    @After
    public void tearDown() throws Exception {
        realm.close();
    }

    @Test
    public void firstTest() {
        presenter.getPlansByGoal("mass");

        //verify(exListView$$State).updatePlansByGoal();
    }

    private void fillDb() {
        Gson gson = new GsonBuilder().setLenient().create();

        String file = "assets/plan.json";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);

        plan_goal_array = gson.fromJson(new InputStreamReader(in), PlanGoal[].class);

        realm.beginTransaction();
        for (int i = 0; i < plan_goal_array.length; i++) {
            PlanGoal plan_goal = plan_goal_array[i];
            realm.copyToRealm(plan_goal);
        }
        realm.commitTransaction();
    }
}
