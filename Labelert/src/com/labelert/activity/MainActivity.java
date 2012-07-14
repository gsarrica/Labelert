package com.labelert.activity;


import com.labelert.fragment.LabelertPreferenceFragment;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new LabelertPreferenceFragment()).commit();
    }

    
}
