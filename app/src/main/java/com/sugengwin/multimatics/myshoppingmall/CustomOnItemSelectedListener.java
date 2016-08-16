package com.sugengwin.multimatics.myshoppingmall;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by Multimatics on 22/07/2016.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private OnItemSelectedCallback onItemSelectedCallback;
    private int position;
    private TextView tvSubTotal;

    public CustomOnItemSelectedListener(int position, TextView tvSubTotal, OnItemSelectedCallback onItemSelectedCallback) {
        this.position = position;
        this.tvSubTotal = tvSubTotal;
        this.onItemSelectedCallback = onItemSelectedCallback;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        onItemSelectedCallback.onItemSelected(view, tvSubTotal, position, i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnItemSelectedCallback {
        void onItemSelected(View view, TextView tvSubTotal, int itemRowPosition, int itemArrayPosition);
    }
}
