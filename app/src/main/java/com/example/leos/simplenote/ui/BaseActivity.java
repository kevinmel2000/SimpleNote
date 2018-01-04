package com.example.leos.simplenote.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.leos.simplenote.R;

/**
 * Created by Leo on 03/01/2018.
 */

public class BaseActivity extends AppCompatActivity {
    //transaction fragment

    protected Fragment getFragmentByTag(String TAG){
        return getSupportFragmentManager().findFragmentByTag(TAG);
    }

    protected void replaceFragment(Fragment fragment, int container, String TAG, String titleBar){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(container, fragment, TAG)
                .addToBackStack(null)
                .commit();
        if (titleBar != null){
            getSupportActionBar().setTitle(titleBar);
        }
    }

    protected void showFragment(Fragment fragment, String titleBar){
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commit();
        if (titleBar != null){
            getSupportActionBar().setTitle(titleBar);
        }
    }
}
