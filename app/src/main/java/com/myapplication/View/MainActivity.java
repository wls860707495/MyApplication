package com.myapplication.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import com.myapplication.R;

public class MainActivity extends AppCompatActivity implements Fragment1.Communication {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Log.d("MainActivity", "selected: " + item.getItemId());

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    PushFragment(new Fragment1(), "fragment1");
                    return true;
                case R.id.navigation_dashboard:
                    PushFragment(new Fragment2(), "fragment2");
                    return true;
                case R.id.navigation_notifications:
                    PushFragment(new Fragment3(), "fragment3");
                    return true;
                case R.id.navigation_statistic:
                    PushFragment(new Fragment4(),"统计测试");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void PushFragment(Fragment fragment, String fragmentTag){
        if (fragment == null){
            Log.d("PushFragment", "fragment is null");
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null){
            Log.d("PushFragment", "fragmentManager is null");
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public String foo() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null){
            Log.d("foo", "fragmentManager is null");
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentByTag("fragment2");
            fragment2.bar("Hello Fragment");
            fragmentTransaction.commit();
        }

        return "Hello from Main activity";
    }
}
