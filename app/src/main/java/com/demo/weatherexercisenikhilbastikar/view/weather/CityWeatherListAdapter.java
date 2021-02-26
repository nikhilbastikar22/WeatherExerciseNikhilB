package com.demo.weatherexercisenikhilbastikar.view.weather;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.weatherexercisenikhilbastikar.R;
import com.demo.weatherexercisenikhilbastikar.databinding.CityWeatherListItemBinding;

import java.util.List;

public class CityWeatherListAdapter extends RecyclerView.Adapter<CityWeatherListAdapter.CityWeatherViewHolder> {

    /* Properties */
    private List<com.demo.weatherexercisenikhilbastikar.model.List> dataSet;
    private CityWeatherClickListener mClickListener;

    public CityWeatherListAdapter(CityWeatherClickListener clickListener) {
        mClickListener = clickListener;
    }

    /* Adapter methods */
    public void updateDataSource(List<com.demo.weatherexercisenikhilbastikar.model.List> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityWeatherListItemBinding view =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.city_weather_list_item, parent, false);
        return new CityWeatherViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(final CityWeatherViewHolder holder, final int listPosition) {
        com.demo.weatherexercisenikhilbastikar.model.List result = dataSet.get(listPosition);
        holder.onBind(result);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public static class CityWeatherViewHolder extends RecyclerView.ViewHolder {

        private CityWeatherListItemBinding listItemBinding;
        private com.demo.weatherexercisenikhilbastikar.model.List result;

        /* Initializations */
        public CityWeatherViewHolder(@NonNull CityWeatherListItemBinding itemBinding, CityWeatherClickListener clickListener) {
            super(itemBinding.getRoot());
            listItemBinding = itemBinding;
            itemView.setOnClickListener(v -> clickListener.onNewsClicked(result));
        }

        public void onBind(com.demo.weatherexercisenikhilbastikar.model.List result) {
            this.result = result;
            listItemBinding.weatherCondition.setText("Weather: "+result.getWeather().get(0).getDescription());
            listItemBinding.temp.setText("Temp: "+result.getMain().getTemp());
            listItemBinding.date.setText(result.getDtTxt());
        }
    }
}