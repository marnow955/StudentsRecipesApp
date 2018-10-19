package com.github.marnow955.studentsrecipesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marek Noworolnik on 13.10.2018.
 */

public class RecipeActivity extends AppCompatActivity{

    ImageView recipeImage;
    TextView recipeSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeImage = (ImageView) findViewById(R.id.recipe_pic);
        recipeSteps = (TextView) findViewById(R.id.recipe_steps);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");
        setTitle(recipe.getRecipeName());
        recipeImage.setImageResource(recipe.getRecipe_pic_id());
        recipeSteps.setText(recipe.getRecipeSteps());
    }
}
