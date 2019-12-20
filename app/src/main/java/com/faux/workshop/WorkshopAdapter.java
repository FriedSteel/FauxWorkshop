package com.faux.workshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.WorkshopViewHolder> {

    private Context mContext;
    private List<Model> modelList;

    public WorkshopAdapter(Context context, List<Model> modelList) {

        mContext = context;
        this.modelList = modelList;
    }

    public class WorkshopViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, companyText;

        public WorkshopViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.workshop_title);
            companyText = itemView.findViewById(R.id.company_title);
        }
    }

    @NonNull
    @Override
    public WorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.applied_layout, parent, false);
        return new WorkshopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopViewHolder holder, final int position) {
        final Model model = modelList.get(position);

        holder.titleText.setText(model.getName());
        holder.companyText.setText(model.getCompany());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer id = model.getId();
                String name = model.getName();
                String company = model.getCompany();
                String duration = model.getDuration();
                String location = model.getLocation();
                Integer fee = model.getFee();
                String applied = model.getApplied();

                Intent intent = new Intent(mContext, WorkshopInfo.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("company", company);
                intent.putExtra("duration", duration);
                intent.putExtra("location", location);
                intent.putExtra("fee", fee);
                intent.putExtra("applied", applied);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
