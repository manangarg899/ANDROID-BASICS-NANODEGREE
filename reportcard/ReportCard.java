package com.example.manan.reportcard;

import static com.example.manan.reportcard.R.id.marks;

/**
 * Created by Manan on 22-01-2017.
 */

public class ReportCard {

    private int mMarks;

    private String mName;


    public ReportCard(String name,int marks) {
        setmName(name);
        setmMarks(marks);
    }

    public String getmName() {
        return mName;
    }

    public int getmMarks() {
        return mMarks;
    }

    public void setmMarks(int marks) {
        if(marks > 90)
            mMarks = 10;
        else if(marks > 80)
            mMarks = 9;
        else if(marks > 70)
            mMarks = 8;
        else if(marks > 60)
            mMarks = 7;
        else
            mMarks = 6;
    }

    public void setmName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "Name : "+getmName()+"\nMarks : "+getmMarks();
    }

}
