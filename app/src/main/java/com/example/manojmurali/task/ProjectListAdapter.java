package com.example.manojmurali.task;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {

    private LayoutInflater mlayoutInflator;
    private List<Project> mProjects;
    private OnItemClickListener listener;


    ProjectListAdapter(Context context) {
        mlayoutInflator = LayoutInflater.from(context);

    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final TextView pname;
        private final TextView domain;


        ProjectViewHolder(final View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.text_pname);
            domain = itemView.findViewById(R.id.text_domain);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null & position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mProjects.get(position));
                    }
                }
            });
        }
    }


    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mlayoutInflator.from(parent.getContext())
                .inflate(R.layout.projectitem_view, parent, false);

        return new ProjectViewHolder(itemView);

    }


    public void onBindViewHolder(ProjectViewHolder holder, int position) {

        Project currentProject = mProjects.get(position);
        holder.pname.setText(currentProject.getPname());
        holder.domain.setText(currentProject.getDomain());
    }


    @Override
    public int getItemCount() {
        if (mProjects != null) {
            return mProjects.size();
        } else return 0;
    }


    Project getProjectAt(int position) {
        return mProjects.get(position);
    }


    void setmProjects(List<Project> mProjects) {
        this.mProjects = mProjects;
        notifyDataSetChanged();
    }


    interface OnItemClickListener {
        void onItemClick(Project project);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
