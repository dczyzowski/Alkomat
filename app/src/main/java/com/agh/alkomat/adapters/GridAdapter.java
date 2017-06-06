package com.agh.alkomat.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agh.alkomat.R;
import com.agh.alkomat.models.DrinkModel;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<DrinkModel> {

    LayoutInflater mInflater;
    ArrayList<DrinkModel> drinkModels;

    public GridAdapter(@NonNull Context context, @LayoutRes int resource,
                       @NonNull ArrayList<DrinkModel> objects) {
        super(context, resource, objects);

        drinkModels = objects;
        mInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder{
        TextView gridTitle;
        TextView gridVoltage;
        TextView gridQuantity;
        TextView timeLeft;
        ImageView drinkImage;

        int position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_element, null);

            holder = new ViewHolder();
            holder.gridTitle = (TextView) convertView.findViewById(R.id.drink_title);
            holder.gridQuantity = (TextView) convertView.findViewById(R.id.quantity);
            holder.gridVoltage = (TextView) convertView.findViewById(R.id.voltage);
            holder.timeLeft = (TextView) convertView.findViewById(R.id.time_left);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.position = position;

        try {
            holder.gridTitle.setText(drinkModels.get(position).getDrinkName());
            holder.gridVoltage.setText(drinkModels.get(position).getVolume() + "%");
            holder.gridQuantity.setText(drinkModels.get(position).getQuantity() + "ml");
            if(drinkModels.get(position).getTimeToRest() > 0)
                holder.timeLeft.setText(drinkModels.get(position).getTimeLeft());
            else{
                drinkModels.remove(position);
            }
        }
        catch (IndexOutOfBoundsException excep){
            Log.d("Index", excep.toString());
        }



        return convertView;
    }
}
