package com.github.marnow955.studentsrecipesapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @StyleableRes
    int recipeNameIndex = 0;
    @StyleableRes
    int recipeImageIndex = 1;
    @StyleableRes
    int recipeStepsIndex = 2;

    String[] recipes_names;
    TypedArray recipes_pics;

    List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null && savedInstanceState.containsKey("recipes")) {
            Recipe[] recipesArray = (Recipe[]) savedInstanceState.getParcelableArray("recipes");
            startRecipesListFragment(new ArrayList(Arrays.asList(recipesArray)));
            return;
        }
        startRecipesListFragment(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable[] parcelablesRecipes = recipes.toArray(new Recipe[0]);
        outState.putParcelableArray("recipes", parcelablesRecipes);
    }

    private void startRecipesListFragment(List<Recipe> recipeList) {
//        TypedArray ta = getResources().obtainTypedArray(R.array.recipes);
//        int n = ta.length();
//        for (int i = 0; i < n; ++i) {
//            int id = ta.getResourceId(i, 0);
//            if (id > 0) {
//                String[] rec = getResources().getStringArray(id);
//                System.out.println("XD: "+id+" "+rec[0]+" "+rec[1]);
//            } else {
//                // something wrong with the XML
//            }
//        }
//        ta.recycle();
        if (recipeList == null) {
            recipes = new ArrayList<>();

//            recipes_names = getResources().getStringArray(R.array.recipes_names);
//            recipes_pics = getResources().obtainTypedArray(R.array.recipes_pictures);
//
//            for (int i = 0; i < recipes_names.length; i++) {
//                Recipe recipe = new Recipe(recipes_names[i], recipes_pics.getResourceId(i, -1));
//                recipes.add(recipe);
//            }
            TypedArray recipesData = getResources().obtainTypedArray(R.array.recipes);
            for (int i = 0; i < recipesData.length(); i++) {
                int id = recipesData.getResourceId(i, 0);
                if (id > 0) {
                    TypedArray rec = getResources().obtainTypedArray(id);
                    Recipe recipe = new Recipe(
                            rec.getString(recipeNameIndex),
                            rec.getResourceId(recipeImageIndex, -1),
                            rec.getString(recipeStepsIndex)
                    );
                    recipes.add(recipe);
                }
            }
        } else {
            recipes = recipeList;
        }
        RecipesListFragment recipesListFragment = new RecipesListFragment();
        recipesListFragment.setRecipes(recipes);
        getFragmentManager().beginTransaction()
                .replace(R.id.recipes_list_fragment, recipesListFragment).commit();
    }

    protected void removeRecipe(Recipe recipe) {
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe r = iterator.next();
            if (r.getRecipeName().equals(recipe.getRecipeName())) {
                iterator.remove();
                break;
            }
        }
    }
}
