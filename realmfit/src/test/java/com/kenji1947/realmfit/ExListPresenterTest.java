package com.kenji1947.realmfit;

import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.scr_ex_catalog_presenter.ExListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class ExListPresenterTest {
    @Mock
    Realm realm;
    @Mock
    RealmList<Exercise> realmList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void firstTest() {
        ExListPresenter presenter = new ExListPresenter(realm);

        realmList = new RealmList<>();

    }
}
