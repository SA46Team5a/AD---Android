package com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.DisbursementListDeptAdapter;
import com.example.wanglu.stationerystore.Model.ChangeCollectionPointModel;
import com.example.wanglu.stationerystore.Model.DeptRepModel;
import com.example.wanglu.stationerystore.Model.DisbursementDetailModel;
import com.example.wanglu.stationerystore.Model.DisbursementListDeptModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Author: Luo Chao, Wang Lu and Jack
public class DisbursementListDeptActivity extends AppCompatActivity {
    private ConstraintLayout disbursementInclude=null;
    ListView itemsListView;
    Spinner departmentDropdownlist;
    TextView collectionlLabel;
    TextView collectionView;
    TextView representativeLabel;
    TextView representativenameView;
    EditText codeView;
    Button confirmBtn;
    String selectedDept;
    String selectedDeptId;
    ArrayList<String > deptIDList = new ArrayList<>();
    ArrayList<String> deptNameList = new ArrayList<>();
    DisbursementListDeptAdapter adapter;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbersment_confirm);

        initializeViews();
        setEventListeners();
        new fillDropDown().execute();
   }

    protected void initializeViews(){
        disbursementInclude = findViewById(R.id.disbursementInclude);
        itemsListView = findViewById(R.id.itemsListView);
        confirmBtn = findViewById(R.id.confirmButton);
        adapter = new DisbursementListDeptAdapter(this);
        representativeLabel = findViewById(R.id.representativeLabel);
        collectionView = findViewById(R.id.collectionView);
        representativenameView = findViewById(R.id.representativenameView);
        codeView = findViewById(R.id.codeView);
        codeView.setHint("Enter Code");

        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        departmentDropdownlist=findViewById(R.id.departmentDropdownlist);
    }

    protected void setEventListeners() {
        // set EventListener for confirm button

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            // TODO: add asynctask for restarting activity
            public void onClick(View v) {
                new submitDisbursementList().execute();


           }
        });

        // set EventListener for dropdown list
        departmentDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDept= adapterView.getItemAtPosition(i).toString();
                selectedDeptId =deptIDList.get(deptNameList.indexOf(selectedDept));
                new getDisbursementList().execute(selectedDeptId);
                new getCollectionPoint().execute(selectedDeptId);
                new getDepRep().execute(selectedDeptId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"You must select one department",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Start Async Task to fill drop down menu
    protected class fillDropDown extends AsyncTask<Void, Void, HashMap<String, ArrayList<String>>> {
        @Override
        protected HashMap<String, ArrayList<String>> doInBackground(Void... params) {
            Log.i("Async", "fillDropDown fired");
            HashMap<String, ArrayList<String>> deptMap = new HashMap<>();
            deptMap = DisbursementListDeptModel.getDepartment();
            return deptMap;
        }
        @Override
        protected void onPostExecute(HashMap<String, ArrayList<String>> result) {
            Log.i("Async", "fillDropDown received");
            Log.i("Async", result.toString());
            deptIDList = result.get("DepartmentID");
            deptNameList = result.get("DepartmentName");

            final ArrayAdapter<String> dropdownlistAdapter=new ArrayAdapter<String>(DisbursementListDeptActivity.this,android.R.layout.simple_spinner_item, deptNameList);
            if (deptIDList.size() > 0) {
                Log.i("Async", "fillDropDown not null");
                dropdownlistAdapter.setDropDownViewResource(R.layout.spinner_item);
                departmentDropdownlist.setAdapter(dropdownlistAdapter);
                departmentDropdownlist.setSelection(0);
                new getDisbursementList().execute(deptIDList.get(0));
            }
            else
                Toast.makeText(DisbursementListDeptActivity.this, "No disbursements available", Toast.LENGTH_SHORT).show();
       }
    }

    protected class getCollectionPoint extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return ChangeCollectionPointModel.getCollectionPointOfDept(strings[0]);
        }

        @Override
        protected void onPostExecute(String collectionPoint) {
            collectionView.setText(collectionPoint);
        }
    }

    protected class getDepRep extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return DeptRepModel.getDeptRepOfDept(strings[0]).getEmpName();
        }

        @Override
        protected void onPostExecute(String empName){
            representativenameView.setText(empName);
        }
    }

    protected class getDisbursementList extends AsyncTask<String, Void, List<DisbursementDetailModel>> {
        @Override
        protected List<DisbursementDetailModel> doInBackground(String... strings) {
            List<DisbursementDetailModel> l = DisbursementDetailModel.getDisbursementDetailsOfDepartment(strings[0]);
            Log.i("getDisbursementList", l.toString());
            return DisbursementDetailModel.getDisbursementDetailsOfDepartment(strings[0]);
        }

        @Override
        protected void onPostExecute(List<DisbursementDetailModel> disDetails) {
            if (disDetails != null) {
                adapter.setData(disDetails);
                itemsListView.setAdapter(adapter);
            } else {
                Toast.makeText(DisbursementListDeptActivity.this, "No disbursements for this department", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected class submitDisbursementList extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            String passcode = codeView.getText().toString();
            String empId = pref.getString("empID","no name");
            if (adapter.validateData()) {
                boolean result = DisbursementDetailModel.submitDisbursementDetails(adapter.getData(), selectedDeptId, empId, passcode);
                return result;
            } else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                recreate();
            }
            else {
                Toast.makeText(DisbursementListDeptActivity.this, "There are incomplete fields. Please complete them before submission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
