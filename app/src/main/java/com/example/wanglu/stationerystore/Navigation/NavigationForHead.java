package com.example.wanglu.stationerystore.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep.AppointRepActivity;
import com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm.ApproveRequestFormActivity;
import com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint.UpdateLocationActivity;
import com.example.wanglu.stationerystore.DepRequisition.DelegateAuthority.DelegateAuthorityActivity;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval.StationeryRetrievalFormActivity;

public class NavigationForHead extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_head);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button approvalbtn = (Button) findViewById(R.id.approverequest_btn);
        approvalbtn.setTag(1);
        Button delegatebtn = (Button) findViewById(R.id.delegateauthority_btn);
        delegatebtn.setTag(2);
        Button appointbtn = (Button) findViewById(R.id.appointdept_btn);
        appointbtn.setTag(3);
        Button changebtn = (Button) findViewById(R.id.changepoint_btn);
        changebtn.setTag(4);

        approvalbtn.setOnClickListener(clickListener);
        delegatebtn.setOnClickListener(clickListener);
        appointbtn.setOnClickListener(clickListener);
        changebtn.setOnClickListener(clickListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (Integer) v.getTag();
            switch (tag){
                case 1:
                    Intent intent= new Intent(NavigationForHead.this, ApproveRequestFormActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Intent intent2 = new Intent(NavigationForHead.this,DelegateAuthorityActivity.class);
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(NavigationForHead.this,AppointRepActivity.class);
                    startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent(NavigationForHead.this,UpdateLocationActivity.class);
                    startActivity(intent4);
                    break;
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.approvalform) {
            // Handle the camera action
            Intent intent= new Intent(this, ApproveRequestFormActivity.class);
            startActivity(intent);
        } else if (id == R.id.delegateauthority) {
            Intent intent= new Intent(this, DelegateAuthorityActivity.class);
            startActivity(intent);
        } else if (id == R.id.appointrep) {
            Intent intent= new Intent(this, AppointRepActivity.class);
            startActivity(intent);
        } else if (id == R.id.changepoint) {
            Intent intent= new Intent(this, UpdateLocationActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
