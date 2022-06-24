package com.fmu.financesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.databinding.FragmentStatisticsBinding;

public class StatisticsFragment extends Fragment {

    private FragmentStatisticsBinding binding;
    private TextView tvFood, tvEducation, tvMarket, tvHealth, tvTransport, tvOther;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        setPercentages();
        return view;
    }

    private void setPercentages() {
        tvFood = binding.tvStFoodPercentage;
        tvEducation = binding.tvStEducationPercentage;
        tvHealth = binding.tvStHealthPercentage;
        tvMarket = binding.tvStMarketPercentage;
        tvTransport = binding.tvStTransportPercentage;
        tvOther = binding.tvStOtherPercentage;

    }
}