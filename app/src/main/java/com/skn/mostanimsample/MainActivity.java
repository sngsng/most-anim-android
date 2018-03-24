package com.skn.mostanimsample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.skn.mostanimsample.databinding.ActivityMainBinding;
import com.skn.mostanimsample.etc.DuckProgressActivity;
import com.skn.mostanimsample.wash.WashActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.washAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeActivity(WashActivity.class);
            }
        });

        mBinding.progressAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeActivity(DuckProgressActivity.class);
            }
        });
    }

    private void routeActivity(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
