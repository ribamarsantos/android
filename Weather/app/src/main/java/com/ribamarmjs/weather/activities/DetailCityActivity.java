package com.ribamarmjs.weather.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ribamarmjs.weather.R;
import com.ribamarmjs.weather.databinding.ActivityDetailCityBinding;

import model.City;
import util.Util;

public class DetailCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        City city =  getIntent().getParcelableExtra(Util.EXTRA_CITY);
        ActivityDetailCityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_city);
        binding.setCity(city);
    }
}
