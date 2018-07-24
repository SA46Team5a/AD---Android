package com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.DisbursementListDeptAdapter;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
//Author:Luo Chao
public class DisbursementListDeptActivity extends AppCompatActivity {
    private ConstraintLayout disbursementInclude=null;
    ListView itemsListView;
    Spinner departmentDropdownlist;
    TextView collectionlLabel;
    TextView collectionView;
    TextView representativeLabel;
    TextView codeLabel;
    TextView representativenameView;
    EditText codeView;
    String selectedDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbersment_confirm);
        disbursementInclude=findViewById(R.id.disbursementInclude);
        itemsListView=findViewById(R.id.itemsListView);
        DisbursementListDeptAdapter adapter=new DisbursementListDeptAdapter(this);
        itemsListView.setAdapter(adapter);
        representativeLabel=findViewById(R.id.representativeLabel);
        representativeLabel.setText("Representative name");
        collectionView=findViewById(R.id.collectionView);
        representativenameView=findViewById(R.id.representativenameView);
        codeView=findViewById(R.id.codeView);

        //load info to dropdownlist
        final ArrayList<String> deptlist=new ArrayList<String>()
        {
            {
                add("deptA");add("deptB");add("deptC");add("deptD");
            }
        };
        final ArrayList<String> deptIDlist=new ArrayList<String>()
        {
            {
                add("1");add("2");add("3");add("4");
            }
        };
        departmentDropdownlist=findViewById(R.id.departmentDropdownlist);


        final ArrayAdapter<String> dropdownlistAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, deptlist);
        dropdownlistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentDropdownlist.setAdapter(dropdownlistAdapter);
        departmentDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDept= adapterView.getItemAtPosition(i).toString();
                String s =deptIDlist.get( deptlist.indexOf(selectedDept));
                Toast.makeText(DisbursementListDeptActivity.this,s,Toast.LENGTH_SHORT).show();

                collectionView.setText(selectedDept);

                representativenameView.setText(selectedDept);

                codeView.setText(selectedDept);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"You must select one department",Toast.LENGTH_LONG).show();
            }
        });

    }
}
