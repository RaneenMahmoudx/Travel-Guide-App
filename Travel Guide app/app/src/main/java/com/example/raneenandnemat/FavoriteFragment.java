package com.example.raneenandnemat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    Destination destination = new Destination();

    RecyclerView recyclerView;
    Adapter adapter;
    public static List<Destination> favoritesList;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void addToFavorites(Destination destination) {
        favoritesList.add(destination);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.Favorite_list);
        favoritesList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), favoritesList);
        recyclerView.setAdapter(adapter);
        favoritesList.add(destination);
        adapter.notifyDataSetChanged();

        return view;
    }

}