package com.example.techjini.stepcounter.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.example.techjini.stepcounter.R;
import com.example.techjini.stepcounter.listener.SensorListenerService;
import com.example.techjini.stepcounter.fragment.OverviewFragment;
import com.example.techjini.stepcounter.fragment.SettingsFragment;

/**
 * @author Techjini bhaskar
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle b) {
        super.onCreate(b);
        startService(new Intent(this, SensorListenerService.class));
        if (b == null) {
            Fragment newFragment = new OverviewFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(android.R.id.content, newFragment);

            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }

    public boolean optionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStackImmediate();
                break;
            case R.id.action_settings:
                getFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new SettingsFragment()).addToBackStack(null)
                        .commit();
                break;

        }
        return true;
    }
}