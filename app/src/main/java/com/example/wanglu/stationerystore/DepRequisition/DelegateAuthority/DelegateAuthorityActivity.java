package com.example.wanglu.stationerystore.DepRequisition.DelegateAuthority;

import android.app.DatePickerDialog;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Fragments.DatePickerFragment;
import com.example.wanglu.stationerystore.R;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//Author: Luo Chao
public class DelegateAuthorityActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ConstraintLayout delegateLayout=null;
    TextView startText;
    TextView endText;
    Button targetBtn;
    String selectedEmpName;
    String selectedEmpId;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        if(targetBtn.getId()==R.id.startButton)
        {
            startText.setText(currentDateString);
        }
        if(targetBtn.getId()==R.id.endButton)
        {
            endText.setText(currentDateString);
        }

    }
    //create touchEventListener class for multiple touch event use for start/end date editTextView
    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            android.support.v4.app.DialogFragment datePicker=new DatePickerFragment();
             targetBtn= (Button) view;
            datePicker.show(getSupportFragmentManager(),"date picker");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form);

        startText=findViewById(R.id.startdate);
        endText=findViewById(R.id.enddate);
        delegateLayout=findViewById(R.id.delegateInclude);
        Button startBtn=findViewById(R.id.startButton);
        Button endBtn=findViewById(R.id.endButton);
        startBtn.setOnClickListener(new ClickListener());
        endBtn.setOnClickListener(new ClickListener());
        Button confirmBtn=findViewById(R.id.confirmButton);
//        if(true)
//        {
//            startBtn.setEnabled(false);
//        }
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DelegateAuthorityActivity.this,selectedEmpId,Toast.LENGTH_SHORT).show();


            }
        });
        //load dropdownlist
        final ArrayList<String> emplist=new ArrayList<String>()
        {
            {
                add("empA");add("empB");add("empC");add("empD");
            }
        };
        final ArrayList<String> empIDlist=new ArrayList<String>()
        {
            {
                add("1");add("2");add("3");add("4");
            }
        };

        Spinner empDropdownlist = findViewById(R.id.authoritySpinner);

        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, emplist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empDropdownlist.setAdapter(adapter );
        empDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedEmpName= adapterView.getItemAtPosition(i).toString();
                empIDlist.get( emplist.indexOf(selectedEmpName));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                 Toast.makeText(getApplicationContext(),"You must select one employee",Toast.LENGTH_LONG).show();
            }
        });





    }
}
