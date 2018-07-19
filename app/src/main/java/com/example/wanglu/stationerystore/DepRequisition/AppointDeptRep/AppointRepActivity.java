package com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;

public class AppointRepActivity extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<String>()
    {
        {
            add("repA");add("repB");add("repC");add("repD");
        }
    };

    private ConstraintLayout employeedropdownlist = null;
    Spinner spinner;
    Button btn;
    String selected;
   // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_dropdown_item_1line);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form2);
        employeedropdownlist = findViewById(R.id.employeeItems);

        spinner = findViewById(R.id.representativeSpinner);


        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                     selected=adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               // Toast.makeText(getApplicationContext(),"You must select one representative",Toast.LENGTH_LONG).show();
            }
        });
        btn = findViewById(R.id.confirmButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT);
                    t.show();
            }
        });
    }
}
