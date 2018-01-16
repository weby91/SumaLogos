package com.sumalogos.webster.sumalogos.util;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webster on 09/01/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Spanned spanned;

    HomeActivity.OnDataChangedListener onDataChangedListener;

    @Override
    public int getItemCount() {
        return 365;
//        return (null != arrayList ? arrayList.size() : 0);

    }

    public RecyclerViewAdapter(HomeActivity.OnDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {



        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_row, viewGroup, false);
        return new ViewHolder(mainGroup);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        spanned = Html.fromHtml("<a href='#'>Mazmur 5</a>");
        holder.tvVerse1.setMovementMethod(LinkMovementMethod.getInstance());

        Log.d("Weby", "ganti");


        if (onDataChangedListener != null) {
            String day;
            String date;

            if (position % 2 == 0) {
                day = "Kamis";
                date = "1 Januari 2018";
                holder.tvVerse1.setText(spanned);

            } else {
                day = "Sabtu";
                date = "5 Januari 2018";
                holder.tvVerse1.setText("TESTT");

            }
            onDataChangedListener.onDataChanged(day, date);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvVerse1)
        TextView tvVerse1;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
