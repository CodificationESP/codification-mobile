package com.codification.codificationesp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arame on 31/01/2018.
 */

public class AccueilAdapter extends BaseAdapter {
    private Context context;
    private AlertDialog dialogue = null;

    public AccueilAdapter( Context context) {
        this.context = context;
    }

    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListView list;
        View vue;
        final View copvue;
        if (convertView == null) {
            vue = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vue = inflater.inflate(R.layout.accueilcodification,null);
        }
        else{
            vue = convertView;
        }
        copvue = vue;

        return vue;
    }

}
