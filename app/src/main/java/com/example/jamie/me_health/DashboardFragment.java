package com.example.jamie.me_health;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Jamie on 08/03/2018.
 */

public class DashboardFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        Button button1 = (Button) view.findViewById(R.id.testStorageButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView testCalories = (TextView) view.findViewById(R.id.testCaloriesView);
                    TextView testExercise = (TextView) view.findViewById(R.id.testExerciseView);
                    TextView testGoals = (TextView) view.findViewById(R.id.testGoalsView);

                    FileInputStream fis1 = getActivity().openFileInput("calories");
                    FileInputStream fis2 = getActivity().openFileInput("exercise");
                    FileInputStream fis3 = getActivity().openFileInput("goals");

                    InputStreamReader isr1 = new InputStreamReader(fis1);
                    InputStreamReader isr2 = new InputStreamReader(fis2);
                    InputStreamReader isr3 = new InputStreamReader(fis3);

                    BufferedReader bufferedReader1 = new BufferedReader(isr1);
                    BufferedReader bufferedReader2 = new BufferedReader(isr2);
                    BufferedReader bufferedReader3 = new BufferedReader(isr3);

                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    String line;

                    while ((line = bufferedReader1.readLine()) != null) {
                        sb1.append(line);
                    }
                    testCalories.setText(sb1.toString());

                    while ((line = bufferedReader2.readLine()) != null) {
                        sb2.append(line);
                    }
                    testExercise.setText(sb2.toString());

                    while ((line = bufferedReader3.readLine()) != null) {
                        sb3.append(line);
                    }
                    testGoals.setText(sb3.toString());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
