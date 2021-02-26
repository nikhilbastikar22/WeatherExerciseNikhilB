package com.demo.weatherexercisenikhilbastikar.view.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.demo.weatherexercisenikhilbastikar.R;
import com.demo.weatherexercisenikhilbastikar.databinding.HomeFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.databinding.WeatherDetailsFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.databinding.WeatherListFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.model.List;
import com.demo.weatherexercisenikhilbastikar.view.BaseFragment;

public class CityWeatherDetailsFragment extends BaseFragment
{
    private WeatherDetailsFragmentBinding mBinding;
    private List mCityListData;
    public static final String CITY_DETAILS = "city_details";
    public static final String CITY_NAME = "city_name";

    /* Life-cycle methods */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Setup view. */
        mBinding = DataBindingUtil.inflate(inflater, R.layout.weather_details_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List cityDetails = (List) getArguments().getSerializable(CITY_DETAILS);
        String cityName = getArguments().getString(CITY_NAME);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(cityName);
        mCityListData = cityDetails;
        BindData();
    }

    private void BindData()
    {
        if (mCityListData != null)
        {
            mBinding.lblTemp.setText(mCityListData.getMain().getTemp().toString());
            mBinding.lblFeelsLike.setText("Feels Like : "+ mCityListData.getMain().getFeelsLike());
            mBinding.lblWeather.setText("Clouds : "+ mCityListData.getWeather().get(0).getDescription());
        }
    }


}
