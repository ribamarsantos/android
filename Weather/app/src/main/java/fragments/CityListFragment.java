package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.ribamarmjs.weather.R;
import com.ribamarmjs.weather.activities.DetailCityActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.CityAdapter;
import listener.OnCityClickListener;
import model.City;
import network.OpenWeatherLoader;
import util.Util;


public class CityListFragment extends Fragment {

    private LatLng coordinate;
    private List<City> mCities;
    private CityAdapter mAdapter;
    private boolean isFavoriteList;

    // Views
    private RecyclerView mRecyclerView;
    private View mEmptyView;
    private View mLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if ( mCities == null){
            mCities = new ArrayList<>();
        }
        mAdapter = new CityAdapter(mCities, getContext());
        mAdapter.setCityClickListener(mClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_city_list, container, false);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview_City);
        mEmptyView    = layout.findViewById(R.id.empty_result);
        mEmptyView.setVisibility(View.GONE);

        mLoading = layout.findViewById(R.id.loading);

        mRecyclerView.setVisibility(View.GONE);
        mLoading.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);


        setLoader();


        return layout;

    }

    private void setLoader() {

        isFavoriteList = getActivity().getIntent().getBooleanExtra(Util.EXTRA_IS_FAVORITE_LIST, false);
        if(!isFavoriteList) {
            coordinate = getActivity().getIntent().getParcelableExtra(Util.EXTRA_LATLNG);
        }
        initLoader();

    }

    private void initLoader() {
        getActivity().getSupportLoaderManager().initLoader(Util.ID_LOADER, null, loaderCallback);
    }


    private LoaderManager.LoaderCallbacks<List<City>> loaderCallback  = new LoaderManager.LoaderCallbacks<List<City>>() {
        @Override
        public Loader<List<City>> onCreateLoader(int id, Bundle args) {
            return new OpenWeatherLoader(getContext(), coordinate, isFavoriteList);
        }

        @Override
        public void onLoadFinished(Loader<List<City>> loader, List<City> cities) {
            try {
                if( cities != null && cities.size() > 0){
                    mCities.clear();
                    mCities.addAll(cities);
                    mAdapter.notifyDataSetChanged();
                    mEmptyView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }else{
                    mEmptyView.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            } finally {
                mLoading.setVisibility(View.GONE);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<City>> loader) {

        }
    };

    private OnCityClickListener mClickListener = new OnCityClickListener() {
        @Override
        public void onCityClick(City city, int position) {
            Intent it = new Intent(getActivity(), DetailCityActivity.class);
            it.putExtra(Util.EXTRA_CITY, city);
            startActivity(it);
        }
    };
}
