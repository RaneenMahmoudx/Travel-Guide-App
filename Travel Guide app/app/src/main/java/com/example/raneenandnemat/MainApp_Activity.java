package com.example.raneenandnemat;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;


public class MainApp_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    Bundle bundle;

    DataBaseHelper dataBaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBaseHelper = new DataBaseHelper(MainApp_Activity.this, "RaneenAndNemat", null, 1);

        bundle = new Bundle();

        setContentView(R.layout.activity_mainapp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        if (savedInstanceState == null) {
            bundle.putString("EMAIL", dataBaseHelper.getEmail());
            HomeFragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            navigationView.setCheckedItem(R.id.home);
        }




    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.all:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllFragment()).commit();
                break;

            case R.id.Sorted:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SortFragment()).commit();
                break;
            case R.id.Favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                break;
            case R.id.Profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new editproFragment()).commit();
                break;

            case R.id.home:
                bundle.putString("EMAIL", dataBaseHelper.getEmail());
                HomeFragment fragment = new HomeFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;



            case R.id.Logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Logout_Fragment()).commit();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainApp_Activity.this);
                builder.setMessage("Do you want to exit?");
                builder.setCancelable(true);

                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MainApp_Activity.this,LoginActivity.class));

                    }
                });

                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}