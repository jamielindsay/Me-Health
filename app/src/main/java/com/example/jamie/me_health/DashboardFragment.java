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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        try {
            TextView calGained = (TextView) view.findViewById(R.id.calGainedView);
            TextView calLost = (TextView) view.findViewById(R.id.calLostView);
            TextView weightLost = (TextView) view.findViewById(R.id.weightLostView);
            TextView weightTargetV = (TextView) view.findViewById(R.id.weightTargetProgView);
            TextView weightTargetL = (TextView) view.findViewById(R.id.weightTargetProgLabel);

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

            int i;
            String line;
            List<String> caloriesList = new ArrayList<>();
            List<String> exerciseList = new ArrayList<>();
            List<String> timerList;
            List<String> goalsList;
            int caloriesBurnt = 0;
            int caloriesGained = 0;
            float currentWeight = 0f;
            float goalWeight = 0f;
            float weightLossNeeded;

            while ((line = bufferedReader1.readLine()) != null) {
                sb1.append(line);
                caloriesList = Arrays.asList(line.split(","));
            }
            for (i=1; i < caloriesList.size(); i += 2) {
                caloriesGained += Integer.parseInt(caloriesList.get(i));
            }

            while ((line = bufferedReader2.readLine()) != null) {
                sb2.append(line);
                exerciseList = Arrays.asList(line.split(","));
            }
            for (i=1; i < exerciseList.size(); i += 2) {
                timerList = Arrays.asList(exerciseList.get(i).split(":"));
                caloriesBurnt += Integer.parseInt(timerList.get(0)) * 2;
                int x = Integer.parseInt(timerList.get(1));
                if (x > 45) {
                    caloriesBurnt += 2;
                }
                else if (x > 15) {
                    caloriesBurnt += 1;
                }
            }

            while ((line = bufferedReader3.readLine()) != null) {
                sb3.append(line);
                goalsList = Arrays.asList(line.split(","));
                currentWeight = Float.parseFloat(goalsList.get(0));
                goalWeight = Float.parseFloat(goalsList.get(1));
            }

            caloriesBurnt = (caloriesBurnt * 180) + 2000;
            Float weightLoss = ((caloriesBurnt - caloriesGained) / 7700f);

            currentWeight -= weightLoss;
            weightLossNeeded = currentWeight - goalWeight;

            calGained.setText(caloriesGained + " " + getResources().getString(R.string.kcal));
            calLost.setText(caloriesBurnt + " " + getResources().getString(R.string.kcal));
            weightLost.setText(String.format(java.util.Locale.US, "%.2f", weightLoss) + " " + getResources().getString(R.string.kg));
            weightTargetV.setText(String.format(java.util.Locale.US, "%.2f", weightLossNeeded) + " " + getResources().getString(R.string.kg));
            weightTargetL.setText(getResources().getString(R.string.to_target) + String.format(java.util.Locale.US, "%.2f", goalWeight) + getResources().getString(R.string.to_target_append));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
