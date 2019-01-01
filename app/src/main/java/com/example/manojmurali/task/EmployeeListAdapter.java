package com.example.manojmurali.task;


import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class EmployeeListAdapter extends

        RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>  {

    private LayoutInflater mlayoutInflator;
    private List<Employee> mEmployees;
    private OnItemClickListener listener;


    EmployeeListAdapter(Context context) {
        mlayoutInflator = LayoutInflater.from(context);

    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private final TextView fname;
        private final TextView lname;
        private final TextView type;



        EmployeeViewHolder(final View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.text_fname);
            lname = itemView.findViewById(R.id.text_lname);
            type = itemView.findViewById(R.id.text_type);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null & position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mEmployees.get(position));
                    }
                }
            });
        }
    }


    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mlayoutInflator.from(parent.getContext())
                .inflate(R.layout.employeeitem_view, parent, false);

        return new EmployeeViewHolder(itemView);

    }


    public void onBindViewHolder(EmployeeViewHolder holder, int position) {

        Employee currentEmployee = mEmployees.get(position);
        holder.fname.setText(currentEmployee.getFname());
        holder.lname.setText(currentEmployee.getLname());
        holder.type.setText(currentEmployee.getType());
        }


    @Override
    public int getItemCount() {
        if (mEmployees != null) {
            return mEmployees.size();
        } else return 0;
    }


    Employee getEmployeeAt(int position) {
        return mEmployees.get(position);
    }

    void setmEmployees(List<Employee> mEmployees){
        this.mEmployees = mEmployees;
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onItemClick(Employee employee);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
