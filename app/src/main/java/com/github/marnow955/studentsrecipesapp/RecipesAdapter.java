package com.github.marnow955.studentsrecipesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Marek Noworolnik on 12.10.2018.
 */

public class RecipesAdapter extends BaseAdapter {
    Context context;
    List<Recipe> recipes;

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recipes.indexOf(recipes.get(position));
    }

    private class ViewHolder {
        ImageView recipe_pic;
        TextView recipe_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
            holder = new ViewHolder();

            holder.recipe_name = (TextView) convertView.findViewById(R.id.recipe_name);
            holder.recipe_pic = (ImageView) convertView.findViewById(R.id.recipe_pic);

            Recipe row_pos = recipes.get(position);

            holder.recipe_pic.setImageResource(row_pos.getRecipe_pic_id());
            holder.recipe_name.setText(row_pos.getRecipeName());
        }
        return convertView;
    }
}
