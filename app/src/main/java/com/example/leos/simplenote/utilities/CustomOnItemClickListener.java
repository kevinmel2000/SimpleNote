package com.example.leos.simplenote.utilities;

import android.view.View;

/**
 * Created by Leo on 20/09/2017.
 */

public class CustomOnItemClickListener implements View.OnClickListener {

    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback){
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v, position);
    }
    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
