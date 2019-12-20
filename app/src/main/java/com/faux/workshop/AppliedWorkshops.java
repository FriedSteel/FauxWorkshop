package com.faux.workshop;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AppliedWorkshops extends Fragment {

    private WorkshopAdapter mAdapter;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private TextView empty;
    private List<Model> modelList;

    public AppliedWorkshops() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applied_workshops, container, false);

        dbHelper = new DBHelper(getContext());

        recyclerView = view.findViewById(R.id.applied_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        empty = view.findViewById(R.id.empty_applied_text);

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class Preparing extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            dbHelper.addDataModel(new Model("General Art Workshop", "Major Art Inc.", "3 months", "Andheri, Mumbai", 800, "no"));
            dbHelper.addDataModel(new Model("Java Workshop", "The Code Group", "6 months", "Wai, Pune", 600, "no"));
            dbHelper.addDataModel(new Model("Android Workshop", "Google LLC.", "6 months", "Bandra, Mumbai", 1100, "no"));
            dbHelper.addDataModel(new Model("Python in 1 Month!", "Fast Coders", "1 month", "Bandra, Mumbai", 400, "no"));
            dbHelper.addDataModel(new Model("Painting Workshop", "Major Art Inc.", "3 months", "Andheri, Mumbai", 800, "no"));
            dbHelper.addDataModel(new Model("Everything PhotoShop", "Learn New Pvt. Ltd.", "2 months", "Dadar, Mumbai", 500, "no"));
            dbHelper.addDataModel(new Model("Cinematic Filming", "Learn New Pvt. Ltd.", "5 months", "Bandra, Mumbai", 2000, "no"));
            dbHelper.addDataModel(new Model("Cinematic Editing", "Learn New Pvt. Ltd.", "3 months", "Dadar, Mumbai", 600, "no"));
            dbHelper.addDataModel(new Model("JavaScript Basics", "The Code Group", "1 month", "Wai, Pune", 300, "no"));
            dbHelper.addDataModel(new Model("Flutter and Dart", "The Code Group", "1 Year", "Wai, Pune", 3000, "no"));
            dbHelper.addDataModel(new Model("Easy Meditation", "MindField", "3 months", "The Space, Mumbai", 600, "no"));
            dbHelper.addDataModel(new Model("Japanese Book Binding", "Major Art Inc.", "1 month", "Andheri, Mumbai", 200, "no"));
            dbHelper.addDataModel(new Model("Entrepreneur Minds", "MindField", "2 months", "The Space, Mumbai", 600, "no"));
            dbHelper.addDataModel(new Model("Bonjour, Learn French", "Learn New Pvt. Ltd.", "6 months", "Bandra, Mumbai", 1000, "no"));

            modelList = dbHelper.getSummaryDataModelWhere();


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            WorkshopAdapter mAdapter = new WorkshopAdapter(getContext(), modelList);
            recyclerView.setAdapter(mAdapter);

            if (mAdapter.getItemCount() != 0) {
                empty.setVisibility(View.GONE);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    class PreparingOnPost extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            modelList = dbHelper.getSummaryDataModelWhere();

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            mAdapter = new WorkshopAdapter(getContext(), modelList);
            recyclerView.setAdapter(mAdapter);

            if (mAdapter.getItemCount() != 0) {
                empty.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (modelList != null) {
            if (modelList.isEmpty()) {
                new AppliedWorkshops.PreparingOnPost().execute();
                Log.d("TAG", "onResume: model list is empty");
            } else {
                Log.d("TAG", "onResume: model list is NOT empty");
            }
        } else {
            Log.d("TAG", "onResume: model list NULL");

            new AppliedWorkshops.Preparing().execute();
        }
    }
}
