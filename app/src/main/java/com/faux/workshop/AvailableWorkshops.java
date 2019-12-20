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

public class AvailableWorkshops extends Fragment {

    private WorkshopAdapter mAdapter;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    List<Model> modelList;

    TextView empty;

    public AvailableWorkshops() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_available_workshops, container, false);

        dbHelper = new DBHelper(getContext());

        recyclerView = view.findViewById(R.id.available_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        empty = view.findViewById(R.id.empty_available_text);

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class Preparing extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            modelList = dbHelper.getSummaryDataModel();

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
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            modelList = dbHelper.getSummaryDataModel();

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
                new PreparingOnPost().execute();
                Log.d("TAG", "onResume: model list is empty");
            } else {
                Log.d("TAG", "onResume: model list is NOT empty");
            }
        } else {
            Log.d("TAG", "onResume: model list NULL");

            new Preparing().execute();
        }
    }
}
