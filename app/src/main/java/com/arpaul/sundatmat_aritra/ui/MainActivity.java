package com.arpaul.sundatmat_aritra.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.arpaul.sundatmat_aritra.R;
import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.receivers.PowerReceiver;
import com.arpaul.sundatmat_aritra.services.ScreenService;
import com.arpaul.sundatmat_aritra.viewmodel.SleepVM;
import com.arpaul.sundatmat_aritra.viewmodel.UserSleepVM;
import com.arpaul.utilitieslib.CalendarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.arpaul.utilitieslib.CalendarUtils.DATE_FORMAT1;
import static com.arpaul.utilitieslib.CalendarUtils.DATE_TIME_FORMAT1;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_Sleeps)
    protected RecyclerView rv_Sleeps;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    @BindView(R.id.fabDelete)
    protected FloatingActionButton fabDelete;
    @BindView(R.id.tvNooneHere)
    protected TextView tvNooneHere;

    private SleepAdapter adapter;
    private List<Sleep> mSleeps = new ArrayList<>();
    private SleepVM sleepVM;
    private UserSleepVM userSleepVM;
    private PowerReceiver mPowerKeyReceiver = new PowerReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, ScreenService.class));;
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleepVM.deleteAll();
            }
        });

        setAdapter();

        sleepVM = ViewModelProviders.of(this).get(SleepVM.class);
        userSleepVM = ViewModelProviders.of(this).get(UserSleepVM.class);
        sleepVM.getSleep().observe(this, new Observer<List<Sleep>>() {
            @Override
            public void onChanged(@Nullable final List<Sleep> users) {
                mSleeps = users;
                adapter.refresh(users);
                isUserAvailable();
            }
        });
    }

    private void setAdapter() {
        adapter = new SleepAdapter(this, mSleeps);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_Sleeps.setLayoutManager(mLayoutManager);
        rv_Sleeps.setItemAnimator(new DefaultItemAnimator());
        rv_Sleeps.setAdapter(adapter);

        isUserAvailable();
    }

    private void isUserAvailable() {
        if(mSleeps.size() > 0)
            tvNooneHere.setVisibility(View.GONE);
        else
            tvNooneHere.setVisibility(View.VISIBLE);
    }
}
