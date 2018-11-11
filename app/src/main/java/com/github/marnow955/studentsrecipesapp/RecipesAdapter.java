package com.github.marnow955.studentsrecipesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marek Noworolnik on 12.10.2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Recipe item);
    }

    Context context;
    List<Recipe> recipes;
    private final OnItemClickListener listener;

    public RecipesAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        recipes = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(recipes.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipe_pic;
        TextView recipe_name;
        Recipe recipe;

        ViewHolder(View itemView) {
            super(itemView);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_pic = itemView.findViewById(R.id.recipe_pic);
        }

        void bind(final Recipe recipe, final OnItemClickListener listener) {
            this.recipe = recipe;
            recipe_name.setText(recipe.getRecipeName());
            recipe_pic.setImageResource(recipe.getRecipe_pic_id());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(recipe);
                }
            });
        }
    }
}
