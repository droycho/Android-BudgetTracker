package com.epicodus.budgettracker.adapters;

import android.content.Context;
import android.content.res.Configuration;

import com.epicodus.budgettracker.models.Transaction;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Guest on 7/20/16.
 */
public class FirebaseTransactionListAdapter extends FirebaseRecyclerAdapter<Transaction, FirebaseTransactionViewHolder> {
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private Context mContext;
    private ArrayList<Transaction> mTransactions = new ArrayList<>();
//    private int mOrientation;

    public FirebaseTransactionListAdapter(Class<Transaction> modelClass, int modelLayout,
                                          Class<FirebaseTransactionViewHolder> viewHolderClass,
                                          Query ref, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;

    }

@Override
protected void populateViewHolder(final FirebaseTransactionViewHolder viewHolder, Transaction model, int position) {
    viewHolder.bindTransaction(model);
}
};