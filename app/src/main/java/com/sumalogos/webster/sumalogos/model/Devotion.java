package com.sumalogos.webster.sumalogos.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.sumalogos.webster.sumalogos.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webster on 04/01/18.
 */

public class Devotion extends AbstractItem<Devotion, Devotion.ViewHolder> {

    public int imageResId;
    public int titleResId;

    private String title;
    private int image;

    public Devotion(String title,  int image) {

        this.title = title;

        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public Devotion (int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new Devotion.ViewHolder(v);
    }

    @Override
    public void bindView(Devotion.ViewHolder viewHolder, List<Object> juloPayments) {
        super.bindView(viewHolder, juloPayments);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_card;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
