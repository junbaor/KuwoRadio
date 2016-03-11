package com.junbaor.kuworadio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by Administrator on 2016/1/28.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private final static String GITHUB_URL = "https://github.com/junbaor/KuwoRadio";
    private final static String Weibo_URL = "http://weibo.com/junbaor";
    private final static String EMAIL = "junbaor@qq.com";
    //private final static String XXXXL = "http://coolapk.com/u/421881";
    private final static String WEIXIN = "junbaor";

    private Preference mVersionPref;
    private Preference mGitHubPref;
    private Preference mWeiboPref;
    private Preference mWeiXin;
    private Preference mEmailPref;
    private Preference mThanksXXXXL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        mVersionPref = findPreference("version");
        mGitHubPref = findPreference("github");
        mWeiboPref = findPreference("weibo");
        mWeiXin = findPreference("weixin");
        mEmailPref = findPreference("email");
        mThanksXXXXL = findPreference("icon_thanks");

        mGitHubPref.setOnPreferenceClickListener(this);
        mWeiboPref.setOnPreferenceClickListener(this);

        mVersionPref.setSummary("1.0 Bata");
        mGitHubPref.setSummary(GITHUB_URL);
        mWeiboPref.setSummary(Weibo_URL);
        mWeiXin.setSummary(WEIXIN);
        mEmailPref.setSummary(EMAIL);
        mThanksXXXXL.setSummary("感谢github");

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mWeiboPref) {
            Uri uri = Uri.parse(Weibo_URL);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
            return true;
        }
        if (preference == mGitHubPref) {
            Uri uri = Uri.parse(GITHUB_URL);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
            return true;
        }
        return true;
    }
}
