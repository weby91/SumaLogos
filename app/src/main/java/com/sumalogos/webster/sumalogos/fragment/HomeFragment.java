package com.sumalogos.webster.sumalogos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.util.CoverFlowAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by webster on 04/01/18.
 */

public class HomeFragment extends Fragment {
    View vFragment;

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.tvTest)
//    TextView textView;

    @BindView(R.id.cardView)
    CardView cardView;

    @BindView(R.id.coverFlow)
    FeatureCoverFlow coverFlow;

    private CoverFlowAdapter mAdapter;

    private ArrayList<Devotion> mData = new ArrayList<>(0);

    private FastItemAdapter<Devotion> fastItemAdapter;

    private List<Devotion> devotions;

    private TextSwitcher mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData.add(new Devotion(R.drawable.mountain_1, R.string.login_with_google));
        mData.add(new Devotion(R.drawable.mountain_2, R.string.login_with_facebook));
        mData.add(new Devotion(R.drawable.mountain_1, R.string.login_with_google));
        mData.add(new Devotion(R.drawable.mountain_2, R.string.login_with_facebook));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vFragment = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, vFragment);

//        textView.setText("asd");

        initFastAdapter();

        return vFragment;
    }

    private void initFastAdapter() {
        fastItemAdapter = new FastItemAdapter<>();

        devotions = new ArrayList<>();

        Devotion devotion;

        for (int i = 0; i < 3; i++) {
//            devotion = new Devotion();
//            devotions.add(devotion);
        }

//        mAdapter = new CoverFlowAdapter()

//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, errorArray);


//        recyclerView = vFragment.findViewById(R.id.recyclerView);

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
//        recyclerView.setAdapter(fastItemAdapter);
//
//        coverFlow.setAdapter(fastItemAdapter);

        fastItemAdapter.add(devotions);

    }
}
