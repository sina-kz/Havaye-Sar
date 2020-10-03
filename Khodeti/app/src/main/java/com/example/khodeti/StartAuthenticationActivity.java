package com.example.khodeti;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StartAuthenticationActivity extends Activity {

    private final String[] companyNames = {"کارگزاری", "بانک مرکزی حمهوری اسلامی", "بیمه"};
    private Spinner companies;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_authentication_activity);

        configSpinner();
    }

    private void configSpinner(){
        companies = (Spinner) findViewById(R.id.company_name) ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, companyNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companies.setAdapter(adapter);
    }

}
