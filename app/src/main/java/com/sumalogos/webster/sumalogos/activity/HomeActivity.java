package com.sumalogos.webster.sumalogos.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.model.UserAndDevotion;
import com.sumalogos.webster.sumalogos.util.AppDatabase;
import com.sumalogos.webster.sumalogos.util.DevotionAdapter;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;
import com.sumalogos.webster.sumalogos.util.Status;
import com.sumalogos.webster.sumalogos.util.Utils;
import com.sumalogos.webster.sumalogos.viewmodel.HomeViewModel;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webster on 09/01/18.
 */

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.seekBar2)
    SeekBar seekBar;

    @BindView(R.id.tvDay)
    TextView tvDay;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private int readingProgress = 0;

    private int backCount = 0;

    private HomeViewModel homeViewModel;

    private AppDatabase db;

    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("public");

        Utils.getInstance(this).setIsLogin(true);

        setContentView(R.layout.recyclerview);

        ButterKnife.bind(this);

        initData();

        initSeekBar();

        initViews();

        populatRecyclerView();
    }

    private void initSeekBar() {
        seekBar.setEnabled(false);

        seekBar.setProgress(readingProgress);
    }

    private void initViews() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelper = new LinearSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);

    }

    @SuppressWarnings("unchecked")
    private void initUserData() {
        LiveData<LiveDataResult<?>> liveData;

        liveData = homeViewModel.retrieveReadingProgress(userId);

        Observer observer = new Observer<LiveDataResult<?>>() {
            @Override
            public void onChanged(@Nullable LiveDataResult<?> result) {
                liveData.removeObserver(this);

                if (result != null && result.getStatus() == Status.SUCCESS) {
                    User user = (User) result.getData();

                    if (user != null) {
                        readingProgress = user.getReadingProgress();

                        initSeekBar();

                        setReadingProgress();
                    }
                }


            }};

        liveData.observe(HomeActivity.this, observer);
    }

    private void initData() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        db = AppDatabase.getInstance();

        userId = db.userDAO().getUserId();

        readingProgress = db.userDAO().getReadingProgress();

        if (readingProgress == 0) {
            initUserData();
        } else {
            setReadingProgress();
        }

        updateLoginDt();

    }

    private void setReadingProgress() {
        String progress = readingProgress + " hari";

        tvDate.setText(progress);
    }

    private void updateLoginDt() {

        homeViewModel.updateLoginDt(userId);
    }


    @SuppressWarnings("unchecked")
    private void populatRecyclerView() {

        DevotionAdapter adapter = new DevotionAdapter(db.devotionDAO().getDevotions(),
                this, new BtnSelesaiOnClickListener() {
            @Override
            public void onClick(String action, int devotionalId, View view, int position) {
                LiveData<LiveDataResult<?>> liveData;

                liveData = homeViewModel.finishRead(userId, action, devotionalId);

                Observer observer = new Observer<LiveDataResult<?>>() {
                    @Override
                    public void onChanged(@Nullable LiveDataResult<?> result) {
                        liveData.removeObserver(this);

                        if (result != null && result.getStatus() == Status.SUCCESS) {
                            UserAndDevotion userAndDevotion = (UserAndDevotion) result.getData();

                            if (userAndDevotion != null) {

                                if (userAndDevotion.getUser() != null) {
                                    db.userDAO().updateUser(userAndDevotion.getUser());
                                }

                                if (userAndDevotion.getDevotions() != null) {
                                    db.devotionDAO().updateDevotions(userAndDevotion.getDevotions());
                                }

                            }

                            readingProgress++;

                            setReadingProgress();
                        }


                    }};

                liveData.observe(HomeActivity.this, observer);
            }
        });

        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        int currentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);

        recyclerView.scrollToPosition(currentDayOfYear - 1);
    }

    public interface BtnSelesaiOnClickListener {
        void onClick(String action, int devotionalId, View view, int position);
    }
    @Override
    public void onBackPressed() {
        backCount++;
        if (backCount == 1) {
            Toast.makeText(this,
                    "Tekan sekali lagi untuk keluar dari aplikasi Summa Logos",
                    Toast.LENGTH_SHORT).show();
        } else if (backCount > 1) {
            this.finish();
        }
    }
}
