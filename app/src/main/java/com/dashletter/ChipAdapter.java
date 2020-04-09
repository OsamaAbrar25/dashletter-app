package com.dashletter;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.dashletter.ui.home.HomeFragment;
import com.dashletter.ui.home.HomeViewModel;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ChipAdapter extends RecyclerView.Adapter<ChipAdapter.ChipHolder> {

    private ArrayList<ChipInfo> arrayList;
    private HomeFragment homeFragment;
    private RecyclerView homeRecyclerView;

    public ChipAdapter(ArrayList<ChipInfo> arrayList, HomeFragment homeFragment) {
        this.arrayList = arrayList;
        this.homeFragment = homeFragment;
        this.homeRecyclerView = homeRecyclerView;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chip_holder, parent, false);
        return new ChipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipHolder holder, int position) {
        holder.chip.setText(arrayList.get(position).getChipName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ChipHolder extends RecyclerView.ViewHolder {

        Chip chip;
        public ChipHolder(@NonNull final View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip_1);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), ""+arrayList.get(getAdapterPosition()).getChipName(), Toast.LENGTH_SHORT).show();
                    PreferenceManager.getDefaultSharedPreferences(itemView.getContext()).edit().putString("CATEGORY_URL",arrayList.get(getAdapterPosition()).getChipUrl()).apply();
                    //homeFragment.getChildFragmentManager().beginTransaction().detach(homeFragment);
                    //homeFragment.getChildFragmentManager().beginTransaction().attach(homeFragment);
                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                    itemView.getContext().startActivity(intent);
                    homeFragment.getActivity().overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim);
                    homeFragment.getActivity().finish();


                }
            });
        }
    }
}
