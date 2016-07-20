package com.epicodus.budgettracker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.models.Transaction;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/19/16.
 */
public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {
    private ArrayList<Transaction> mTransactions = new ArrayList<>();
    private Context mContext;

    public TransactionListAdapter(Context context, ArrayList<Transaction> transactions) {
        mContext = context;
        mTransactions = transactions;
    }

    @Override
    public TransactionListAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_item, parent, false);
        TransactionViewHolder viewHolder = new TransactionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionListAdapter.TransactionViewHolder holder, int position) {
        holder.bindTransaction(mTransactions.get(position));
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.dateTextView) TextView mDateTextView;
        @Bind(R.id.amountTextView) TextView mAmountTextView;

        private Context mContext;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTransaction(Transaction transaction) {
            Log.d("ListAdapter: ", "in bindTransaction");
            mCategoryTextView.setText(transaction.getCategory());
            mDateTextView.setText(transaction.getDate());
            mAmountTextView.setText(String.valueOf(transaction.getAmount()));
        }

    }
}
