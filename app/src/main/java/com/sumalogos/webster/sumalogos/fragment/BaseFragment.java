package com.sumalogos.webster.sumalogos.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumalogos.webster.sumalogos.R;

/**
 * Created by webster on 29/01/18.
 */

public class BaseFragment extends DialogFragment {
    private LoadingFragment loadingFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    protected void showProgressDialog() {
        if (loadingFragment != null) {
            return;
        } else {
            loadingFragment = new LoadingFragment();
        }

        loadingFragment.show(getActivity()
                .getSupportFragmentManager(), "loadingFragment");
    }

    protected void cancelProgressDialog() {
        try {
            if (loadingFragment != null) {
                loadingFragment.dismissAllowingStateLoss();
                loadingFragment = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
