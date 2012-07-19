package com.labelert.fragment;


import com.labelert.R;
import com.labelert.thread.AccountLoaderThread;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
public class LabelertPreferenceFragment extends PreferenceFragment {
	
	final String ACCOUNT_TYPE_GOOGLE = "com.google";
	final String[] FEATURES_MAIL = {"service_mail"};
	Account[] accounts;
	Context context;
	
	  @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          // Load the preferences from an XML resource
          addPreferencesFromResource(R.xml.preferences);
          context = this.getActivity().getApplicationContext();
          getAccounts();
          
      }

	private void getAccounts() {
		AccountLoaderThread accountLoaderThread = new AccountLoaderThread(this);
		accountLoaderThread.start();
	}
}
