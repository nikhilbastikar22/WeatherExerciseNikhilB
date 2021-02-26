package com.demo.weatherexercisenikhilbastikar.view.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demo.weatherexercisenikhilbastikar.R;
import com.demo.weatherexercisenikhilbastikar.core.apis.GetDataService;
import com.demo.weatherexercisenikhilbastikar.core.apis.RetrofitClientInstance;
import com.demo.weatherexercisenikhilbastikar.databinding.WeatherDetailsFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.databinding.WeatherListFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.model.CityWeather;
import com.demo.weatherexercisenikhilbastikar.model.List;
import com.demo.weatherexercisenikhilbastikar.utils.Consts;
import com.demo.weatherexercisenikhilbastikar.view.BaseFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherListFragment extends BaseFragment
{
    private WeatherListFragmentBinding mBinding;
    public static final String CITY_NAME = "city_name";
    private String mCityName = "";
    private CityWeatherListAdapter mAdapter;
    private java.util.List<List> mApiResult = new ArrayList<>();

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.weather_list_fragment, container, false);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CityWeatherListAdapter(this::onItemClicked);
        mBinding.recyclerView.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetCityWeatherData();
    }

    public void GetCityWeatherData()
    {
        mCityName = getArguments().getString(CITY_NAME);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mCityName);
        //Showing the title
        Navigation.findNavController(mBinding.getRoot())
                .getCurrentDestination().setLabel(mCityName);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<CityWeather> call = service.getCityWeatherData(mCityName, Consts.API_KEY);
        call.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                mBinding.progressBar.setVisibility(View.GONE);
                if (response != null) {
                    CityWeather cityWeather = (CityWeather)response.body();
                    mApiResult.addAll(cityWeather.getList());
                    mAdapter.updateDataSource(cityWeather.getList());
                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                mBinding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Internal methods */
    private void onItemClicked(List result) {
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CityWeatherDetailsFragment.CITY_DETAILS, result);
        bundle.putString(CityWeatherDetailsFragment.CITY_NAME, mCityName);
        navController.navigate(R.id.action_to_weather_details_fragment, bundle);
        hideKeyboard();
    }
}
