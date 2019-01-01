package com.example.manojmurali.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter {


    static class EmployeeSpinnerAdapter extends ArrayAdapter<Employee> {


        public EmployeeSpinnerAdapter(Context context, ArrayList<Employee> countryList) {
            super(context, 0, countryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_row_item, parent, false
                );
            }

            TextView textViewFname = convertView.findViewById(R.id.text_view_fname);
            TextView textViewLname = convertView.findViewById(R.id.text_view_lname);

            Employee currentItem = getItem(position);

            if (currentItem != null) {
                //imageViewFlag.setImageResource(currentItem.getFlagImage());
                textViewFname.setText(currentItem.getFname());
                textViewLname.setText(currentItem.getLname());
            }

            return convertView;
        }
    }

    //PENDING EDITING FOR PROJECT DROP DOWN LIST VIEW
    static class ProjectSpinnerAdapter extends ArrayAdapter<Employee> {
        public ProjectSpinnerAdapter(Context context, ArrayList<Employee> countryList) {
            super(context, 0, countryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_row_item, parent, false
                );
            }


            TextView textViewFname = convertView.findViewById(R.id.text_view_fname);
            TextView textViewLname = convertView.findViewById(R.id.text_view_lname);
            Employee currentItem = getItem(position);

            if (currentItem != null) {
                textViewFname.setText(currentItem.getFname());
                textViewLname.setText(currentItem.getLname());
            }

            return convertView;
        }

    }
}
