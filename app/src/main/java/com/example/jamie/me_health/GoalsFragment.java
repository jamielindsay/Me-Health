package com.example.jamie.me_health;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileOutputStream;

/**
 * Created by Jamie on 10/03/2018.
 */

public class GoalsFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_goals, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        Button button1 = (Button) view.findViewById(R.id.saveGoal);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "goals";
                EditText currentW = (EditText) getActivity().findViewById(R.id.weightInput);
                EditText goalW = (EditText) getActivity().findViewById(R.id.goalInput);
                String fileContents = currentW.getText() + "," + goalW.getText();

                FileOutputStream outputStream;

                try {
                    outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}