package com.github.marnow955.studentsrecipesapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Marek Noworolnik on 11.11.2018.
 */

public class RecipeDetailsFragment extends Fragment {

    private Recipe recipe;
    ImageView recipeImage;
    TextView recipeSteps;
    Button removeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View recipeDetailsFragment = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        recipeImage = recipeDetailsFragment.findViewById(R.id.recipe_pic);
        recipeSteps = recipeDetailsFragment.findViewById(R.id.recipe_steps);
        removeButton = recipeDetailsFragment.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecipeDetailsFragment.class);
                intent.putExtra("recipe_to_remove", recipe);
                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                getFragmentManager().popBackStack();
            }
        });
        return recipeDetailsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecipe();
    }

    private void setRecipe() {
        if (getArguments() != null) {
            recipe = (Recipe) getArguments().get("recipe");
//            getActivity().setTitle(recipe.getRecipeName());
            if (recipe != null) {
                recipeImage.setImageResource(recipe.getRecipe_pic_id());
                recipeSteps.setText(recipe.getRecipeSteps());
            }
        }
    }
}
