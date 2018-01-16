package com.sumalogos.webster.sumalogos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.util.CoverFlowAdapter;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by webster on 08/01/18.
 */

public class MainActivity extends AppCompatActivity {
    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private ArrayList<Devotion> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;
    private boolean mIsFirstEnter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData.add(new Devotion(R.drawable.mountain_1, R.string.login_with_google));
        mData.add(new Devotion(R.drawable.mountain_2, R.string.login_with_facebook));
        mData.add(new Devotion(R.drawable.mountain_1, R.string.login_with_google));
        mData.add(new Devotion(R.drawable.mountain_2, R.string.login_with_facebook));

        mTitle = (TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        mAdapter = new CoverFlowAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        getResources().getString(mData.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                if (mIsFirstEnter) {
                    mCoverFlow.scrollToPosition(0);
                    mIsFirstEnter = false;
                } else {
                    mTitle.setText(getResources().getString(mData.get(position).titleResId));
                }
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });

    }

    public void goNextCover(View view) {
        int itemPosition = mCoverFlow.getScrollPosition();

        if (itemPosition == mAdapter.getCount() - 1) {
            finish();
        } else {
            mCoverFlow.scrollToPosition(itemPosition + 1);
        }



        // if (mData.size() == 1) {
        //
        // } else {
        //     mData.remove(itemPosition);
        //     mAdapter.notifyDataSetChanged();
        // }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
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
}
