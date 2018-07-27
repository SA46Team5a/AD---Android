package com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.wanglu.stationerystore.Model.AppointRepModel;
import com.example.wanglu.stationerystore.Model.DisbursementListDeptModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author:Luo Chao
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
    ArrayList<String > deptIDList = new ArrayList<>();
    ArrayList<String> deptNameList = new ArrayList<>();


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbersment_confirm);
        disbursementInclude = findViewById(R.id.disbursementInclude);
        itemsListView = findViewById(R.id.itemsListView);
        confirmBtn = findViewById(R.id.confirmButton);
        DisbursementListDeptAdapter adapter = new DisbursementListDeptAdapter(this);
        itemsListView.setAdapter(adapter);
        representativeLabel = findViewById(R.id.representativeLabel);
        collectionView = findViewById(R.id.collectionView);
        representativenameView = findViewById(R.id.representativenameView);
        codeView = findViewById(R.id.codeView);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisbursementListDeptActivity.this, NavigationForClerk.class);
                startActivity(intent);
                Toast.makeText(DisbursementListDeptActivity.this, "Submit Successful!", Toast.LENGTH_SHORT).show();
            }
        });

//Start Async Task
        new AsyncTask<Void, Void, HashMap<String, ArrayList<String>>>() {
            @Override
            protected HashMap<String, ArrayList<String>> doInBackground(Void... params) {
                HashMap<String, ArrayList<String>> deptMap = new HashMap<>();
                deptMap = DisbursementListDeptModel.getDepartment();
                return deptMap;
            }
            @Override
            protected void onPostExecute(HashMap<String, ArrayList<String>> result) {
                deptIDList = result.get("DepartmentID");
                deptNameList = result.get("DepartmentName");

                departmentDropdownlist=findViewById(R.id.departmentDropdownlist);

                final ArrayAdapter<String> dropdownlistAdapter=new ArrayAdapter<String>(DisbursementListDeptActivity.this,android.R.layout.simple_spinner_item, deptNameList);
                dropdownlistAdapter.setDropDownViewResource(R.layout.spinner_item);
                departmentDropdownlist.setAdapter(dropdownlistAdapter);

                departmentDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedDept= adapterView.getItemAtPosition(i).toString();

                        String s =deptIDList.get( deptNameList.indexOf(selectedDept));
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

//                        collectionView.setText(selectedDept);
//
//                        representativenameView.setText(selectedDept);

//                      codeView.setText(selectedDept);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(getApplicationContext(),"You must select one department",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.execute();

    }
}
