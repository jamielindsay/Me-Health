package com.example.jamie.me_health;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jamie on 09/03/2018.
 */

public class MainFragment extends Fragment {
    Fragment currentFragment = null;
    FragmentTransaction ft;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_main, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        Button button1 = (Button) view.findViewById(R.id.buttonCalorie);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment = new CaloriesFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, currentFragment);
                ft.commit();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.buttonExercise);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment = new ExerciseFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, currentFragment);
                ft.commit();
            }
        });

        Button button3 = (Button) view.findViewById(R.id.buttonGoals);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment = new GoalsFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, currentFragment);
                ft.commit();
            }
        });
    }
}
