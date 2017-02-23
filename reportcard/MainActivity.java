package com.example.manan.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ReportCard> report = new ArrayList<ReportCard>();
        report.add(new ReportCard("Mathematics",100));
        report.add(new ReportCard("Science",90));
        report.add(new ReportCard("Social Science",70));
        report.add(new ReportCard("Moral Science",80));

        ReportAdapter reportAdapter = new ReportAdapter(this, report);

        ListView listView = (ListView) findViewById(R.id.activity_main);

        listView.setAdapter(reportAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ReportCard reportCard = report.get(position);

                Toast.makeText(MainActivity.this,reportCard.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}