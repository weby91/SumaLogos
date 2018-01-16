package com.sumalogos.webster.sumalogos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.util.RecyclerViewAdapter;

import java.util.ArrayList;

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

    //String and Integer array for Recycler View Items
    public static final String[] TITLES= {"Hood","Full Sleeve Shirt","Shirt","Jean Jacket","Jacket"};
    public static final Integer[] IMAGES= {R.drawable.mountain_1,R.drawable.mountain_2,R.drawable.mountain_1,R.drawable.mountain_2,R.drawable.mountain_1,};


    private static String navigateFrom;//String to get Intent Value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.recyclerview);

        ButterKnife.bind(this);

        initSeekBar();


        initViews();
        populatRecyclerView();
    }

    private void initSeekBar() {
        seekBar.setEnabled(false);
    }

    // Initialize the view
    private void initViews() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Set Back Icon on Activity

        navigateFrom = getIntent().getStringExtra("navigateFrom");//Get Intent Value in String

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelper = new LinearSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);





        //Set RecyclerView type according to intent value
//        if (navigateFrom.equals("horizontal")) {
//            getSupportActionBar().setTitle("Horizontal Recycler View");
//            recyclerView
//                    .setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
//        } else {
//            getSupportActionBar().setTitle("Staggered GridLayout Manager");
//            recyclerView
//                    .setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));// Here 2 is no. of columns to be displayed
//
//        }
    }


    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<Devotion> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            arrayList.add(new Devotion(TITLES[i],IMAGES[i]));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(new OnDataChangedListener() {
            @Override
            public void onDataChanged(String day, String date) {
//                tvDay.setText(day);
//                tvDate.setText(date);
            }
        });


//        RecyclerView.AdapterDataObserver dataObserver = new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeChanged(int positionStart, int itemCount) {
//                super.onItemRangeChanged(positionStart, itemCount);
//                Log.d("weby", "a");
//            }
//
//            @Override
//            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
//                super.onItemRangeChanged(positionStart, itemCount, payload);
//                Log.d("weby", "b");
//            }
//
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                Log.d("weby", "c");
//            }
//
//            @Override
//            public void onItemRangeRemoved(int positionStart, int itemCount) {
//                super.onItemRangeRemoved(positionStart, itemCount);
//                Log.d("weby", "d");
//            }
//        };
//        adapter.registerAdapterDataObserver(dataObserver);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

        adapter.notifyDataSetChanged();// Notify the adapter



    }

    public interface OnDataChangedListener {
        void onDataChanged(String day, String date);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
