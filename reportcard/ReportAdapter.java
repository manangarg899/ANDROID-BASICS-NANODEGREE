package com.example.manan.reportcard;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by Manan on 22-01-2017.
 */

public class ReportAdapter extends ArrayAdapter<ReportCard> {

    public ReportAdapter(Activity context, ArrayList<ReportCard> report) {
        super(context, 0, report);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View list_view = convertView;

        if (list_view == null) {
            list_view = LayoutInflater.from(getContext()).inflate(
                    R.layout.view, parent, false);
        }

        ReportCard currentReport = getItem(position);

        TextView grade = (TextView) list_view.findViewById(R.id.name);

        grade.setText(currentReport.getmName());

        TextView marks = (TextView) list_view.findViewById(R.id.marks);

        marks.setText(String.valueOf(currentReport.getmMarks()));

        return list_view;
    }
}
