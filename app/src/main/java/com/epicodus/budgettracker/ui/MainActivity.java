package com.epicodus.budgettracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.budgettracker.Constants;
import com.epicodus.budgettracker.R;
import com.epicodus.budgettracker.models.Transaction;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//    private DatabaseReference mTransactionReference;

    private Button mSubmitButton;
    private EditText mCategoryEditText;
    private EditText mAmountEditText;
    private EditText mDateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryEditText = (EditText) findViewById(R.id.categoryEditText);
        mAmountEditText = (EditText) findViewById(R.id.amountEditText);
        mDateEditText = (EditText) findViewById(R.id.dateEditText);


        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String mCategory = mCategoryEditText.getText().toString();
                double mAmount = Double.parseDouble(mAmountEditText.getText().toString());
                String mDate = mDateEditText.getText().toString();

                Transaction transaction = new Transaction(mCategory, mAmount, mDate);

                saveToFirebase(transaction);

                startActivity(intent);
            }
        });
    }

    private void saveToFirebase(Transaction transaction) {
        DatabaseReference transactionRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TRANSACTIONS);
        transactionRef.push().setValue(transaction);
        Toast.makeText(MainActivity.this, "Transaction Saved", Toast.LENGTH_SHORT).show();
    }


}
