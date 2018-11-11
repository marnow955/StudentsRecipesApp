package com.github.marnow955.studentsrecipesapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Marek Noworolnik on 10.11.2018.
 */

public class RecipesListFragment extends Fragment {

    private RecyclerView recipesRecyclerView;
    private List<Recipe> recipes;
    private RecipesAdapter recipesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View recipesListFragment = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        recipesRecyclerView = recipesListFragment.findViewById(R.id.recipes_list);
        return recipesListFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipesAdapter = new RecipesAdapter(getContext());
        recipesRecyclerView.setAdapter(recipesAdapter);
        recipesAdapter.setRecipes(recipes);
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
