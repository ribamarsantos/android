package com.ribamarmjs.weather.activities;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ribamarmjs.weather.R;
import com.ribamarmjs.weather.databinding.ActivityDetailCityBinding;
import com.squareup.picasso.Picasso;

import dao.CityRepository;
import model.City;
import util.Util;

public class DetailCityActivity extends AppCompatActivity {
    private ActivityDetailCityBinding binding;
    private City city;
    private ShareActionProvider mShareActionProvider;
    private Intent mShareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        city =  getIntent().getParcelableExtra(Util.EXTRA_CITY);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_city);
        binding.setCity(city);
        if(city.getIdBD() != 0) {
            binding.fabFav.setVisibility(View.GONE);
            binding.txtCreated.setVisibility(View.VISIBLE);
        }
        binding.fabFav.setOnClickListener(clickListener);
        createShareIntent(city);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        MenuItem item = menu.findItem(R.id.mnu_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        if (mShareIntent != null){
            mShareActionProvider.setShareIntent(mShareIntent);
        }
        return  true;
    }

    private void createShareIntent(City city) {
        mShareIntent = new Intent(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_text) +
                "#"+city.getName() + " " +
                String.valueOf(city.getTemperature().getTempMax()) + "˚/" +
                String.valueOf(city.getTemperature().getTempMin()) + "˚ - " +
                String.valueOf(city.getWeather().get(0).getDescription())
        ) ;
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }
    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    private View.OnClickListener clickListener= new View.OnClickListener() {

        public void onClick(View view) {
            CityRepository cityRepository = new CityRepository(getApplicationContext());
            if ( cityRepository.isFavorite(city.getId())){
                Toast.makeText(DetailCityActivity.this, getResources().getText(R.string.msg_already_favorite), Toast.LENGTH_SHORT).show();
            }else {
                cityRepository.insert(city);
                Toast.makeText(DetailCityActivity.this, getResources().getText(R.string.msg_saved_successfully), Toast.LENGTH_SHORT).show();
            }
        }
    };

}
