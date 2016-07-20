package com.epicodus.budgettracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.epicodus.budgettracker.Constants;
import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.models.Transaction;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewTransactionActivity extends AppCompatActivity {

//    private DatabaseReference mTransactionReference;

    private Button mSubmitButton;
    private Button mHomeButton;
    private Spinner mCategorySpinner;
    private EditText mAmountEditText;
    private EditText mDateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        mCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        mAmountEditText = (EditText) findViewById(R.id.amountEditText);
        mDateEditText = (EditText) findViewById(R.id.dateEditText);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Housing");


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        mCategorySpinner.setAdapter(spinnerAdapter);

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewTransactionActivity.this, HomeActivity.class);
                String mCategory = mCategorySpinner.getSelectedItem().toString();
                double mAmount = Double.parseDouble(mAmountEditText.getText().toString());
                String mDate = mDateEditText.getText().toString();

                Transaction transaction = new Transaction(mCategory, mAmount, mDate);

                saveToFirebase(transaction);

                startActivity(intent);
            }
        });

        mHomeButton = (Button) findViewById(R.id.homeButton);
        mHomeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewTransactionActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveToFirebase(Transaction transaction) {
        DatabaseReference transactionRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TRANSACTIONS);
        transactionRef.push().setValue(transaction);
        Toast.makeText(NewTransactionActivity.this, "Transaction Saved", Toast.LENGTH_SHORT).show();
    }


}
