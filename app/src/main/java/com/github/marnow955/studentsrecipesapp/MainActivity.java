package com.github.marnow955.studentsrecipesapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    String[] recipes_names;
    TypedArray recipes_pics;

    List<Recipe> recipes;
//    ListView recipesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipes = new ArrayList<>();

        recipes_names = getResources().getStringArray(R.array.recipes_names);
        recipes_pics = getResources().obtainTypedArray(R.array.recipes_pictures);

        for (int i = 0; i < recipes_names.length; i++) {
            Recipe recipe = new Recipe(recipes_names[i], recipes_pics.getResourceId(i, -1));
            recipes.add(recipe);
        }

        RecipesListFragment recipesListFragment = new RecipesListFragment();
        recipesListFragment.setRecipes(recipes);
        getFragmentManager().beginTransaction()
                .add(R.id.recipes_list_fragment, recipesListFragment).commit();

//        recipesListView = (ListView) findViewById(R.id.recipes_list);
//        RecipesAdapter adapter = new RecipesAdapter(this, recipes);
//        recipesListView.setAdapter(adapter);

//        recipesListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
        intent.putExtra("recipe", recipes.get(position));
        startActivity(intent);
    }
}
