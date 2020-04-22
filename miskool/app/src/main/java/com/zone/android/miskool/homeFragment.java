package com.zone.android.miskool;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 21-09-2018.
 */

public class homeFragment extends Fragment implements homeFragmentInterface {

    public homeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        LineChart lineChart = (LineChart) rootView.findViewById(R.id.linechart);


        List<ChartData> values1=new ArrayList<>();

        values1.add(new ChartData(60f, "Malayalam")); //values.add(new ChartData(y,x));<br />
        values1.add(new ChartData(45f, "Maths"));
        values1.add(new ChartData(73f, "Science"));
        values1.add(new ChartData(80f, "English"));
        values1.add(new ChartData(100f, "Total"));

        lineChart.setData(values1);
        lineChart.setDescription("Sub-Marks");
        return rootView;



    }

    @Override
    public void setExamDetails() {

    }
}
