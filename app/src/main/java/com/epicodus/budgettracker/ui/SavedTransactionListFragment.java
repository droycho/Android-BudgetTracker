package com.epicodus.budgettracker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.epicodus.budgettracker.Constants;
import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.adapters.FirebaseTransactionListAdapter;
import com.epicodus.budgettracker.adapters.FirebaseTransactionViewHolder;
import com.epicodus.budgettracker.models.Transaction;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedTransactionListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.newTransactionButton) Button mNewTransactionButton;

    private FirebaseTransactionListAdapter mFirebaseAdapter;


    public SavedTransactionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_transaction_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();

        mNewTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setUpFirebaseAdapter() {
        Query query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_TRANSACTIONS);

        mFirebaseAdapter = new FirebaseTransactionListAdapter(Transaction.class, R.layout.transaction_list_item, FirebaseTransactionViewHolder.class, query, getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
    }


}
