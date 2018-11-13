package com.github.marnow955.studentsrecipesapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Marek Noworolnik on 10.11.2018.
 */

public class RecipesListFragment extends Fragment implements RecipesAdapter.OnItemClickListener {

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
        recipesAdapter = new RecipesAdapter(getContext(), this);
        recipesRecyclerView.setAdapter(recipesAdapter);
        recipesAdapter.setRecipes(recipes);
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public void onItemClick(Recipe item) {
        if (getActivity().getFragmentManager()
                .findFragmentById(R.id.recipe_details_fragment) != null) {
            getActivity().getFragmentManager().popBackStack();
        }
        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setTargetFragment(RecipesListFragment.this, getTargetRequestCode());
        Bundle args = new Bundle();
        args.putParcelable("recipe", item);
        recipeDetailsFragment.setArguments(args);
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.recipe_details_fragment, recipeDetailsFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Recipe recipe = data.getParcelableExtra("recipe_to_remove");
            Iterator<Recipe> iterator = recipes.iterator();
            while (iterator.hasNext()) {
                Recipe r = iterator.next();
                if (r.getRecipeName().equals(recipe.getRecipeName())) {
                    iterator.remove();
                    break;
                }
            }
            recipesAdapter.notifyDataSetChanged();
            ((MainActivity) getActivity()).removeRecipe(recipe);
        }
    }
}
