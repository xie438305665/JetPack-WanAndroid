package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.user.R

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/3
 **/
class SettingPreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.user_fragment_setting)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        preference ?: return super.onPreferenceTreeClick(preference)
        when (preference.key) {
            "preference_color" -> "preference_color".logD()
            "preference_animation" -> "preference_animation".logD()
            "preference_article" -> "preference_article".logD()
            "preference_tag" -> "preference_tag".logD()
            "preference_model" -> "preference_model".logD()
        }
        return super.onPreferenceTreeClick(preference)
    }
}