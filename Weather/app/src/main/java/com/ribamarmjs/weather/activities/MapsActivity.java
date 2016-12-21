package com.ribamarmjs.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ribamarmjs.weather.R;

import util.Util;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mMarker;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener(mOnMapClickListerer);
    }

    private GoogleMap.OnMapClickListener mOnMapClickListerer = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mMap.clear();
            mMarker = null;
            MarkerOptions place = new MarkerOptions().position(latLng).draggable(true);
            mMarker = mMap.addMarker(place);
            mLatLng = latLng;
        }
    };

    public void getListCity(View view) {
        if ( mMarker == null){
            Toast.makeText(this, getResources().getString(R.string.msg_withoutMarker), Toast.LENGTH_SHORT).show();
        }else{
            Intent itListCity = new Intent(this, ListCityActivity.class);
            itListCity.putExtra(Util.EXTRA_LATLNG, mLatLng);
            startActivity(itListCity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.mnuit_favorite){
            Intent itListCity = new Intent(this, ListCityActivity.class);
            itListCity.putExtra(Util.EXTRA_IS_FAVORITE_LIST, true);
            startActivity(itListCity);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }
}
