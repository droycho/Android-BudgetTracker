package com.epicodus.budgettracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.models.Transaction;

/**
 * Created by Guest on 7/19/16.
 */
public class FirebaseTransactionViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseTransactionViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindTransaction(Transaction transaction) {
        Log.d("FirebaseViewHolder: ", "in bindTransaction");
        TextView mCategoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
        TextView mDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        TextView mAmountTextView = (TextView) itemView.findViewById(R.id.amountTextView);

        mCategoryTextView.setText(transaction.getCategory());
        mDateTextView.setText(transaction.getDate());
        mAmountTextView.setText(String.valueOf(transaction.getAmount()));
    }

}
