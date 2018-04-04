package com.example.jamie.me_health;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileOutputStream;

/**
 * Created by Jamie on 10/03/2018.
 */

public class ExerciseFragment extends Fragment {
    boolean timerStarted = false;

    TextView timerTextView;
    long startTime = 0;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int milliseconds = (int) (millis / 10);
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            milliseconds = milliseconds % 100;
            seconds = seconds % 60;
            minutes = minutes % 60;

            timerTextView.setText(String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds));

            timerHandler.postDelayed(this, 10);
        }
    };


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_exercise, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        Spinner spinner = (Spinner) view.findViewById(R.id.exerciseInput);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.exercises_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button button1 = (Button) view.findViewById(R.id.saveExercise);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "exercise";
                Spinner eSpinner = (Spinner) getActivity().findViewById(R.id.exerciseInput);
                TextView eTextView = (TextView) getActivity().findViewById(R.id.timer);
                String fileContents = eSpinner.getSelectedItem() + "," + eTextView.getText();

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

        timerTextView = (TextView) view.findViewById(R.id.timer);
        final Button button2 = (Button) view.findViewById(R.id.buttonTimer);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerStarted) {
                    button2.setText("Start Timer");
                    timerStarted = false;
                    timerHandler.removeCallbacks(timerRunnable);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                } else {
                    button2.setText("Stop Timer");
                    timerStarted = true;
                    startTime = System.currentTimeMillis();
                    timerRunnable.run();
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            }
        });
    }
}