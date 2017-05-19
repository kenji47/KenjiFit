package com.kenji1947.realmfit.repo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.observers.TestSubscriber;

/**
 * Created by kenji1947 on 16.05.2017.
 */

public class RepoTest {
    private Realm realm;
    RepositoryDefault repositoryDefault;
    PlanGoal[] plan_goal_array;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RealmConfiguration config =
                new RealmConfiguration.Builder().inMemory().name("test-realm").build();
        realm = Realm.getInstance(config);
        repositoryDefault = new RepositoryDefault();
        fillDb();

    }
    @After
    public void after() {
        realm.close();
    }

    @Test
    public void firstTest() {

        RealmResults<PlanGoal> realmResults = realm.where(PlanGoal.class).findAllAsync();

        TestSubscriber<PlanGoal> testSubscriber = new TestSubscriber<>();
        repositoryDefault.createPlanGoal("new plan").subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

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
