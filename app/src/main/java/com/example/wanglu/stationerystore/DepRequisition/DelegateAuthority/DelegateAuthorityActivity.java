package com.example.wanglu.stationerystore.DepRequisition.DelegateAuthority;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Fragments.DatePickerFragment;
import com.example.wanglu.stationerystore.Model.DelegateAuthorityModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForHead;
import com.example.wanglu.stationerystore.R;

import java.lang.reflect.GenericArrayType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

//Author: Luo Chao
public class DelegateAuthorityActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> idlist=new ArrayList<>();
    String selectedEmpID;
    private ConstraintLayout delegateLayout=null;
    TextView startText;
    TextView endText;
    Button targetBtn;
    Button startBtn;
    Button endBtn;
    Button confirmBtn;
    Button rescindBtn;
    Spinner empDropdownlist;
    String selectedEmpName;
    String currentAuthorityID;

    DateTimeFormatter dateFormatter;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate todayDate;
    String currentAuthorityName;
    SharedPreferences pref;



    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        String currentDateString= df.format(c.getTime());

        // update the start date based on selection in date picker
        if(targetBtn.getId()==R.id.startButton)
        {
            startText.setText(currentDateString);
            startDate=convertStringToDate(currentDateString);
        }
        // update the end date based on selection in date picker
        else if(targetBtn.getId()==R.id.endButton)
        {
            endText.setText(currentDateString);
            endDate=convertStringToDate(currentDateString);
        }

    }
    //create touchEventListener class for multiple touch event use for start/end date editTextView
    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            DatePickerFragment datePickerDialog = new DatePickerFragment();
            long today = todayDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long start, end;


            // configure datepicker for start date button
            if (view.getId() == R.id.startButton) {
                datePickerDialog.setMinDate(today);
                if (endDate!=null){
                    end = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                    datePickerDialog.setMaxDate(end);
                }
            }
            // configure datepicker for end date button
            else if (view.getId() == R.id.endButton) {
                if (startDate == null)
                    datePickerDialog.setMinDate(today);
                else {
                    start = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                    datePickerDialog.setMinDate(start);
                }
            }

            targetBtn = (Button) view;
            datePickerDialog .show(getSupportFragmentManager(),"date picker");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form);

        dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

        todayDate=LocalDate.now();


        Log.i("######################showValueOftodayDate",dateFormatter.format(todayDate));


        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        startText = findViewById(R.id.startdate);
        endText = findViewById(R.id.enddate);
        delegateLayout = findViewById(R.id.delegateInclude);
        startBtn = findViewById(R.id.startButton);
        endBtn = findViewById(R.id.endButton);
        startBtn.setOnClickListener(new ClickListener());
        endBtn.setOnClickListener(new ClickListener());
        empDropdownlist = findViewById(R.id.authoritySpinner);
        confirmBtn=findViewById(R.id.confirmButton);
        rescindBtn=findViewById(R.id.rescindbutton);

        //disable two control by default
        toggleStartConfirmBtnSpinnerState(false);

//        confirmBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(DelegateAuthorityActivity.this, selectedEmpID, Toast.LENGTH_SHORT).show();
//            }
//        });



        //load current authority info and set two control state
        new AsyncTask<Void, Void, HashMap<String,String>>() {
            @Override
            protected HashMap<String,String> doInBackground(Void... params) {
                HashMap<String,String> currentAuthorityMap=new HashMap<String,String>();

                currentAuthorityMap=DelegateAuthorityModel.getCurrentAuthority(pref.getString("deptID","no name"));
                return currentAuthorityMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,String> result) {
                if(result.size() == 0){
                    //load  Dept namelist to dropdownlist
                    toggleStartConfirmBtnSpinnerState(true);
                    new getEligibleEmployees().execute();
                }
                else {
                    currentAuthorityName = result.get("EmployeeName").toString();
                    currentAuthorityID=result.get("EmployeeID").toString();
                    startDate=convertStringToDate(result.get("StartDate"));
                    endDate=convertStringToDate(result.get("EndDate"));
                    //disable controls if today's date is within the authority duration

                    Log.i("@@@@@@@@@@@@@@@@@@ISstartDateBefTodayDate&&ValueOftodayDate",String.valueOf(startDate.isBefore(todayDate))+"&&"+todayDate.toString());
                    if(!todayDate.isAfter(startDate)){
                        toggleStartConfirmBtnSpinnerState(true);
                    }

                    startText.setText(dateFormatter.format(startDate));
                    endText.setText(dateFormatter.format(endDate));


                    nameList.add(currentAuthorityName);
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DelegateAuthorityActivity.this,android.R.layout.simple_spinner_item, nameList);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    empDropdownlist.setAdapter(adapter );
                    empDropdownlist.setSelection(nameList.indexOf(currentAuthorityName));
                }
            }
        }.execute();


        //two button click event
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startText.getText().toString().isEmpty()||endText.getText().toString().isEmpty())
                {
                    Toast.makeText(DelegateAuthorityActivity.this, "Please choose start and end date for delegation duration!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String title = "Delegate Authority";
                    String msg = "Delegated authority will be submitted. Would you like to continue?";
                    makeAlertDialog(title, msg);
                }
            }
        });
        rescindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title="Rescind Authority";
                String msg="Current authority will be rescinded. Would you like to continue?";
                makeAlertDialog(title,msg);
            }
        });
    }

    void makeAlertDialog(final String title, String msg){
        new AlertDialog.Builder(DelegateAuthorityActivity.this)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                if(title.equals("Delegate Authority")) {
                                    if(currentAuthorityName != null) {
                                        DelegateAuthorityModel.updateAuthority(currentAuthorityID,
                                                convertStringToDate(startText.getText().toString()),
                                                convertStringToDate(endText.getText().toString()));
                                       // Toast.makeText(getApplicationContext(),"Delegation period was updated",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        DelegateAuthorityModel.submitNewAuthority(selectedEmpID,
                                                convertStringToDate(startText.getText().toString()),
                                                convertStringToDate(endText.getText().toString()));
                                       // Toast.makeText(getApplicationContext(),"Authority was granted",Toast.LENGTH_SHORT).show();
                                    }


                                }
                                else if(title.equals("Rescind Authority"))
                                {
                                    DelegateAuthorityModel.rescind(currentAuthorityID);
                                    toggleStartConfirmBtnSpinnerState(true);
                                }
                                return null;
                            }
                        }.execute();
                        startActivity(new Intent(getApplicationContext(), NavigationForHead.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DelegateAuthorityActivity.this, getString(android.R.string.no), Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
//method used in this activity
    private LocalDate convertStringToDate(String sourceString)
    {
        LocalDate d=null;
        try {
            d= LocalDate.parse(sourceString,dateFormatter);

        }catch (Exception e)
        {
            Log.e("convertStringToDate()", "parser error");
        }
        return d;
    }

    //toggle startBtn,spinner
    private void toggleStartConfirmBtnSpinnerState(Boolean b)
    {
        empDropdownlist.setEnabled(b);
        startBtn.setEnabled(b);


    }

     private class getEligibleEmployees extends AsyncTask<Void, Void, HashMap<String,ArrayList<String>>> {
        @Override
        protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
            HashMap<String,ArrayList<String>> empMap=new HashMap<String,ArrayList<String>>();
            empMap=DelegateAuthorityModel.getEmps(pref.getString("deptID","no name"));
            return empMap;
        }
        @Override
        protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
            nameList= result.get("name");
            idlist=result.get("ID");


            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DelegateAuthorityActivity.this,android.R.layout.simple_spinner_item, nameList);
            adapter.setDropDownViewResource(R.layout.spinner_item);
            empDropdownlist.setAdapter(adapter );
//            if(currentAuthorityName != null) {
//                //set current authorityName to dropdownList
//                nameList.add(currentAuthorityName);
//                empDropdownlist.setSelection(nameList.indexOf(currentAuthorityName));
//            }
            empDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedEmpName = adapterView.getItemAtPosition(i).toString();
                    selectedEmpID=idlist.get(nameList.indexOf(selectedEmpName));
                    //Toast.makeText(DelegateAuthorityActivity.this,selectedEmpID,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(getApplicationContext(), "You must select one employee", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

