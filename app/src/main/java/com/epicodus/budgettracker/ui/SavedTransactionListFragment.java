package com.epicodus.budgettracker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.epicodus.budgettracker.Constants;
import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.adapters.FirebaseTransactionListAdapter;
import com.epicodus.budgettracker.adapters.FirebaseTransactionViewHolder;
import com.epicodus.budgettracker.models.Transaction;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedTransactionListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.newTransactionButton) Button mNewTransactionButton;

//    private Spinner mCategorySpinner;

    private FirebaseTransactionListAdapter mFirebaseAdapter;


    public SavedTransactionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_transaction_list, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Rent");
        categories.add("Utilities");
        categories.add("Insurance");
        categories.add("Transportation");
        categories.add("Personal");
        categories.add("Other");

        setUpFirebaseAdapter("Rent");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
//        mCategorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
//        mCategorySpinner.setAdapter(spinnerAdapter);


        mNewTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewTransactionActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setUpFirebaseAdapter(String category) {
        Query query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_TRANSACTIONS)
                .orderByChild("category").equalTo(category);

        mFirebaseAdapter = new FirebaseTransactionListAdapter(Transaction.class, R.layout.transaction_list_item, FirebaseTransactionViewHolder.class, query, getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
    }


}
