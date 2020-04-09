package com.dashletter.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dashletter.ChipAdapter;
import com.dashletter.ChipInfo;
import com.dashletter.ChipViewModel;
import com.dashletter.HomeAdapter;
import com.dashletter.HomeInfo;
import com.dashletter.R;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ChipViewModel chipViewModel;
    //private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private ChipAdapter chipAdapter;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        chipViewModel = ViewModelProviders.of(this).get(ChipViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = root.findViewById(R.id.progressBar);

        final RecyclerView recyclerView2 = root.findViewById(R.id.chip_recycler_view);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager((getContext()), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setHasFixedSize(true);

        final RecyclerView recyclerView = root.findViewById(R.id.home_recycler_view);
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        homeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ArrayList<HomeInfo>>() {
            @Override
            public void onChanged(ArrayList<HomeInfo> homeInfos) {

                progressBar.setVisibility(View.INVISIBLE);
                homeAdapter = new HomeAdapter(homeInfos);
                homeAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(homeAdapter);
            }
        });
        chipViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ChipInfo>>() {
            @Override
            public void onChanged(ArrayList<ChipInfo> chipInfos) {
                chipAdapter = new ChipAdapter(chipInfos, HomeFragment.this);
                recyclerView2.setAdapter(chipAdapter);
            }
        });

        return root;
    }
}
