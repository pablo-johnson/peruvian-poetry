package com.johnson.pablo.poesiaperuana.presentation.poetsList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.pablo.poesiaperuana.R;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.presentation.adapters.PoetsRecyclerViewAdapter;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsFragment;

import java.util.List;


public class PoetsListFragment extends PoetsFragment implements PoetsListView {


    private OnFragmentInteractionListener mListener;
    private PoetsListPresenter presenter;
    private RecyclerView poetsRecyclerView;

    public PoetsListFragment() {
        // Required empty public constructor
    }


    public static PoetsListFragment newInstance() {
        PoetsListFragment fragment = new PoetsListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PoetsListPresenter(this);
        presenter.populatePoetsList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poets, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        poetsRecyclerView = (RecyclerView) view.findViewById(R.id.poetsRecyclerView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void populatePoetsList(List<Poet> poets) {
        poetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        poetsRecyclerView.setAdapter(new PoetsRecyclerViewAdapter(getContext(), poets));
    }


    public interface OnFragmentInteractionListener {

    }
}
