package com.example.laundryui.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryui.R;
import com.example.laundryui.adapter.HomeAdapter;
import com.example.laundryui.databinding.FragmentHomeBinding;
import com.example.laundryui.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    Context mContext;
    HomeAdapter adapter;
    List<Item> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.discover;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setAdapter();
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);

    }
    private void setAdapter() {
        initModel();
     adapter=new HomeAdapter(mContext,list);
     recyclerView.setAdapter(adapter);
    }

    private void initModel() {
        list= new ArrayList<>();
        list.add(new Item(R.drawable.bg_post1,"ABC","PAK","1.2km"));
        list.add(new Item(R.drawable.bg_post2,"ABC","PAK","1.2km"));
        list.add(new Item(R.drawable.bg_post3,"ABC","PAK","1.2km"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}