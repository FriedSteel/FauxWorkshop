package com.faux.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WorkshopInfo extends AppCompatActivity {

    Button applyBtn, appliedBtn;
    TextView Name, Company, Duration, Location, Fee;

    DBHelper dbHelper;
    UserSession userSession;

    private Integer getId;
    private String getName;
    private String getCompany;
    private String getDuration;
    private String getLocation;
    private String getFee;
    private String getApplied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_info);

        userSession = new UserSession(this);
        dbHelper = new DBHelper(this);

        getId = getIntent().getExtras().getInt("id");
        getName = getIntent().getExtras().getString("name");
        getCompany = getIntent().getExtras().getString("company");
        getDuration = getIntent().getExtras().getString("duration");
        getLocation = getIntent().getExtras().getString("location");
        getFee = getIntent().getExtras().get("fee").toString();
        getApplied = getIntent().getExtras().getString("applied");

        Name = findViewById(R.id.workshop_title);
        Name.setText(getName);

        Company = findViewById(R.id.company_title);
        Company.setText(getCompany);

        Duration = findViewById(R.id.duration_title);
        Duration.setText(getDuration);

        Location = findViewById(R.id.location_title);
        Location.setText(getLocation);

        Fee = findViewById(R.id.fee_title);
        Fee.setText(getFee);

        applyBtn = findViewById(R.id.apply_btn);
        appliedBtn = findViewById(R.id.applied_btn);

        if (getApplied.equals("no")) {
            appliedBtn.setVisibility(View.GONE);
        } else {
            applyBtn.setVisibility(View.GONE);
        }

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!userSession.checkLogin()) {
                    dbHelper.updateDataModel(getId);

                    Intent intent = new Intent(WorkshopInfo.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Successfully Applied!", Toast.LENGTH_SHORT).show();
                } else {
                    userSession.checkLogin();
                    Toast.makeText(getApplicationContext(), "Please Log in first", Toast.LENGTH_LONG).show();
                }

            }
        });

        appliedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkshopInfo.this, "You've already applied for this workshop", Toast.LENGTH_LONG).show();
            }
        });
    }

}
