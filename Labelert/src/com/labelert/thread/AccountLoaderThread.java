package com.labelert.thread;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.labelert.R;

public class AccountLoaderThread extends Thread {
	
	private PreferenceFragment fragment;
	private Account[] accounts;
	final String ACCOUNT_TYPE_GOOGLE = "com.google";
	final String[] FEATURES_MAIL = {"service_mail"};
	
	public AccountLoaderThread(PreferenceFragment fragment) {
		super();
		this.fragment = fragment;
	}

	@Override
	public void run() {
		AccountManager accountManager = AccountManager.get(fragment.getActivity().getApplicationContext());
		AccountManagerFuture<Account[]> accountsByTypeAndFeatures = accountManager.getAccountsByTypeAndFeatures(ACCOUNT_TYPE_GOOGLE, FEATURES_MAIL,
        		  null, null);
		while(!accountsByTypeAndFeatures.isDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		try {
			accounts = (Account[]) accountsByTypeAndFeatures.getResult();
			PreferenceCategory accountsCategory = (PreferenceCategory)fragment.getPreferenceManager().findPreference(fragment.getString(R.string.accounts_category_key));
			for (Account account : accounts) {
				PreferenceScreen preferenceScreen = fragment.getPreferenceManager().createPreferenceScreen(fragment.getActivity().getApplicationContext());
				
				preferenceScreen.setKey(account.name);
				preferenceScreen.setTitle(account.name);
				accountsCategory.addPreference(preferenceScreen);
			}
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (AuthenticatorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
