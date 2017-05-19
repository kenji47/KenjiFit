package com.kenji1947.realmfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kenji1947.realmfit.scr_ex_catalog.ExCatalogActivity;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class DummyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.buttonNext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DummyActivity.this, ExCatalogActivity.class);
                startActivity(i);
            }
        });
    }
}
