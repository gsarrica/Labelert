package com.labelert.fragment;

import com.labelert.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class LabelertPreferenceFragment extends PreferenceFragment {
	  @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          // Load the preferences from an XML resource
          addPreferencesFromResource(R.xml.preferences);
      }
}
