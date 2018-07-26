package com.example.wanglu.stationerystore.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.wanglu.stationerystore.Orders.restockInventory.RestockInventoryActivity;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.ManageInventoryDetailsActivity;
import com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList.DisbursementListDeptActivity;
import com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval.StationeryRetrievalFormActivity;
import com.example.wanglu.stationerystore.StockAdjustment.MonthlyInventory.ManageInventoryActivity;
// Wang Lu
public class NavigationForClerk extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_clerk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button retrievalbtn = (Button) findViewById(R.id.retrievalform_btn);
        retrievalbtn.setTag(1);
        Button confirmdisbursementbtn = (Button) findViewById(R.id.confirmdisbursement_btn);
        confirmdisbursementbtn.setTag(2);
        Button restockinbentorybtn = (Button) findViewById(R.id.restockinventory_btn);
        restockinbentorybtn.setTag(3);
        Button managerinventorybtn = (Button) findViewById(R.id.managerinventory_btn);
        managerinventorybtn.setTag(4);

        retrievalbtn.setOnClickListener(clickListener);
        confirmdisbursementbtn.setOnClickListener(clickListener);
        restockinbentorybtn.setOnClickListener(clickListener);
        managerinventorybtn.setOnClickListener(clickListener);


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
                   Intent intent= new Intent(NavigationForClerk.this, StationeryRetrievalFormActivity.class);
                   startActivity(intent);
                   break;
               case 2:
                   Intent intent2 = new Intent(NavigationForClerk.this,DisbursementListDeptActivity.class);
                   startActivity(intent2);
                   break;
               case 3:
                   Intent intent3 = new Intent(NavigationForClerk.this,RestockInventoryActivity.class);
                   startActivity(intent3);
                   break;
               case 4:
                   Intent intent4 = new Intent(NavigationForClerk.this, ManageInventoryActivity.class);
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

        if (id == R.id.createform) {
            Intent intent= new Intent(this, StationeryRetrievalFormActivity.class);
            startActivity(intent);
        } else if (id == R.id.confirmdisbursement) {
            Intent intent = new Intent(this, DisbursementListDeptActivity.class);
            startActivity(intent);
        } else if (id == R.id.restorckinventory) {
            Intent intent = new Intent (this, RestockInventoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.manageinventory) {
            Intent intent = new Intent(this, ManageInventoryActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
