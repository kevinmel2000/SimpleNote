package com.example.leos.simplenote.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.leos.simplenote.R;
import com.example.leos.simplenote.ui.BaseActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    public static final String FRAGMENT_HOME = "fragment_home";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.dl_main) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = getFragmentByTag(FRAGMENT_HOME);

        if (fragment != null){
            showFragment(fragment, "Home");
        }else {
            replaceFragment(new HomeFragment(),
                    R.id.fl_main_container,
                    FRAGMENT_HOME,
                    "Home");
        }
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.nav_home :
                //start main act
                break;
            case R.id.nav_edit :
                //start edit act
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
