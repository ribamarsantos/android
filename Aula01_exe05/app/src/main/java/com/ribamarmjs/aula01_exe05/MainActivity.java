package com.ribamarmjs.aula01_exe05;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;


public class MainActivity extends WearableActivity {


    private BoxInsetLayout mContainerView;

    private Button mBtnTela2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mBtnTela2 = (Button) findViewById(R.id.btnTela2);

        mBtnTela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(it);

            }
        });
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            mBtnTela2.setVisibility(View.INVISIBLE);

        } else {
            mBtnTela2.setVisibility(View.VISIBLE);
            mContainerView.setBackground(null);

        }
    }
}
