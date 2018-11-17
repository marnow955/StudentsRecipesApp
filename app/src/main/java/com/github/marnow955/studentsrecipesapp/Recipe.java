package com.github.marnow955.studentsrecipesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marek Noworolnik on 12.10.2018.
 */

public class Recipe implements Parcelable{

    private String recipeName;
    private int recipe_pic_id;
    private String recipeSteps;

    public Recipe(String recipeName, int recipe_pic_id) {
        this.recipeName = recipeName;
        this.recipe_pic_id = recipe_pic_id;
    }

    public Recipe(String recipeName, int recipe_pic_id, String recipeSteps) {
        this.recipeName = recipeName;
        this.recipe_pic_id = recipe_pic_id;
        this.recipeSteps = recipeSteps;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipe_pic_id() {
        return recipe_pic_id;
    }

    public void setRecipe_pic_id(int recipe_pic_id) {
        this.recipe_pic_id = recipe_pic_id;
    }

    public String getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(String recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(recipeName);
        parcel.writeInt(recipe_pic_id);
        parcel.writeString(recipeSteps);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {

        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private Recipe(Parcel parcel) {
        recipeName = parcel.readString();
        recipe_pic_id = parcel.readInt();
        recipeSteps = parcel.readString();
    }
}
