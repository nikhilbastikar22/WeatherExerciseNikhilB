package com.demo.weatherexercisenikhilbastikar.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.demo.weatherexercisenikhilbastikar.R;
import com.demo.weatherexercisenikhilbastikar.databinding.HomeFragmentBinding;
import com.demo.weatherexercisenikhilbastikar.view.BaseFragment;
import com.demo.weatherexercisenikhilbastikar.view.weather.CityWeatherListFragment;

public class HomeFragment extends BaseFragment
{
    private HomeFragmentBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mBinding.btnLookup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cityName =  mBinding.editTextCityName.getText().toString().trim();
                if (cityName != null || cityName.length() > 0)
                {
                    final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    Bundle bundle = new Bundle();
                    bundle.putString(CityWeatherListFragment.CITY_NAME, cityName);
                    navController.navigate(R.id.action_to_weather_list_fragment, bundle);
                }
                else
                {
                    Toast.makeText(getActivity(), "Please enter City Name.", Toast.LENGTH_LONG).show();
                }
                hideKeyboard();
            }
        });
        return mBinding.getRoot();
    }

}
