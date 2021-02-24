package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    CardView depositcard,transfercard,mobileRechargeCard,utilityBill,historycard,cardrequest,loanrequest,settingcard,newAccountcard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        depositcard = findViewById(R.id.depositCardId);
        transfercard = findViewById(R.id.transferCardId);
        mobileRechargeCard = findViewById(R.id.mobileRechargeCardId);
        utilityBill = findViewById(R.id.utilityBillCardId);
        historycard = findViewById(R.id.historyCardId);
        cardrequest = findViewById(R.id.requestCardCardId);
        loanrequest = findViewById(R.id.requestLoanCardId);
        settingcard = findViewById(R.id.settingCardId);
        newAccountcard = findViewById(R.id.newAccountCardId);


        depositcard.setOnClickListener(this);
        transfercard.setOnClickListener(this);
        mobileRechargeCard.setOnClickListener(this);
        utilityBill.setOnClickListener(this);
        historycard.setOnClickListener(this);
        cardrequest.setOnClickListener(this);
        loanrequest.setOnClickListener(this);
        settingcard.setOnClickListener(this);
        newAccountcard.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.depositCardId){

            Intent intent = new Intent(HomePage.this,DepositMethodActivity.class);
                    Toast.makeText(getApplicationContext(),"Deposit is clicked",Toast.LENGTH_SHORT).show();
                   startActivity(intent);
        }

        if(v.getId() == R.id.transferCardId){

            Intent intent = new Intent(HomePage.this,TransferMethodActivity.class);
            Toast.makeText(getApplicationContext(),"Transfer is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        if(v.getId() == R.id.mobileRechargeCardId){

            Intent intent = new Intent(HomePage.this,MobileRechargeMethod.class);
            Toast.makeText(getApplicationContext(),"Mobile Recharge is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        if(v.getId() == R.id.utilityBillCardId){

            Intent intent = new Intent(HomePage.this,UtitlityBillMethod.class);
            Toast.makeText(getApplicationContext(),"Utility bill is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(v.getId() == R.id.historyCardId){

            Intent intent = new Intent(HomePage.this,HistoryActivity.class);
            Toast.makeText(getApplicationContext(),"History is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(v.getId() == R.id.requestCardCardId){

            Intent intent = new Intent(HomePage.this,RequestCardMethod.class);
            Toast.makeText(getApplicationContext(),"card is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(v.getId() == R.id.requestLoanCardId){

            Intent intent = new Intent(HomePage.this,RequestLoanMethod.class);
            Toast.makeText(getApplicationContext(),"loan is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(v.getId() == R.id.settingCardId){

            Intent intent = new Intent(HomePage.this,SettingActivity.class);
            Toast.makeText(getApplicationContext(),"newaccount is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }if(v.getId() == R.id.newAccountCardId){

            Intent intent = new Intent(HomePage.this,NewAccountActivity.class);
            Toast.makeText(getApplicationContext(),"newAccount is clicked",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}