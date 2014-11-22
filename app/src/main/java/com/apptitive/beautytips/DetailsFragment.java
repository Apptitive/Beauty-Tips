package com.apptitive.beautytips;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptitive.beautytips.adapter.DetailsListAdapter;
import com.apptitive.beautytips.model.Content;
import com.apptitive.beautytips.model.Detail;
import com.apptitive.beautytips.model.JsonDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsFragment extends ListFragment {

    private DetailProvider detailProvider;
    private DetailsListAdapter detailsListAdapter;
    private List<Detail> details;
    private Gson gson;

    public DetailsFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        // EasyTracker.getInstance(getActivity()).activityStart(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        //  EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            detailProvider = (DetailProvider) activity;
        } catch (ClassCastException cce) {
            Log.e(this.getTag(), "Parent activity must implement DetailProvider");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gson = new Gson();
        details = new ArrayList<Detail>();
        details = jsonToDetail(Arrays.asList(gson.fromJson(detailProvider.getContent().getDetails(), JsonDetail[].class)));
        detailsListAdapter = new DetailsListAdapter(getActivity(), details);
        setListAdapter(detailsListAdapter);
    }

    private List<Detail> jsonToDetail(List<JsonDetail> jsonDetails) {
        List<Detail> details = new ArrayList<Detail>();
        for (JsonDetail jsonDetail : jsonDetails) {
            Detail detail1 = new Detail();
            detail1.populateFrom(jsonDetail);
            details.add(detail1);
        }
        return details;
    }

    public void switchContent() {
        setListAdapter(null);
        details.clear();
        details = jsonToDetail(Arrays.asList(gson.fromJson(detailProvider.getContent().getDetails(), JsonDetail[].class)));
        detailsListAdapter.changeDataSet(details);
        setListAdapter(detailsListAdapter);
        detailsListAdapter.notifyDataSetChanged();
    }

    public interface DetailProvider {
        Content getContent();
    }
}