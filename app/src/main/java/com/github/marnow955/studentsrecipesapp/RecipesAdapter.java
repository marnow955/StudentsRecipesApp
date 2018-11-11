package com.github.marnow955.studentsrecipesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marek Noworolnik on 12.10.2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    Context context;
    List<Recipe> recipes;

    public RecipesAdapter(Context context) {
        this.context = context;
        recipes = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }
//
//    @Override
//    public long getItemId(int position) {
//        return recipes.indexOf(recipes.get(position));
//    }

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

        void bind(Recipe recipe) {
            this.recipe = recipe;
            recipe_name.setText(recipe.getRecipeName());
            recipe_pic.setImageResource(recipe.getRecipe_pic_id());
        }
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder = null;
//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.recipe_list_item, null);
//            holder = new ViewHolder();
//
//            holder.recipe_name = (TextView) convertView.findViewById(R.id.recipe_name);
//            holder.recipe_pic = (ImageView) convertView.findViewById(R.id.recipe_pic);
//
//            Recipe row_pos = recipes.get(position);
//
//            holder.recipe_pic.setImageResource(row_pos.getRecipe_pic_id());
//            holder.recipe_name.setText(row_pos.getRecipeName());
//        }
//        return convertView;
//    }
}
