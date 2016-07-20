package com.epicodus.budgettracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.epicodus.budgettracker.Constants;
import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.adapters.FirebaseTransactionListAdapter;
import com.epicodus.budgettracker.adapters.FirebaseTransactionViewHolder;
import com.epicodus.budgettracker.adapters.TransactionListAdapter;
import com.epicodus.budgettracker.models.Transaction;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class  HomeActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.newTransactionButton) Button mNewTransactionButton;
    private TransactionListAdapter mAdapter;
    public ArrayList<Transaction> mTransactions = new ArrayList<>();

    private FirebaseTransactionListAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
//        View view = inflater.inflate(R.layout.transaction_list_item, container, false);

        setUpFirebaseAdapter();

        mNewTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void setUpFirebaseAdapter() {
        Query query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_TRANSACTIONS);

        mFirebaseAdapter = new FirebaseTransactionListAdapter(Transaction.class, R.layout.transaction_list_item, FirebaseTransactionViewHolder.class, query, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}
