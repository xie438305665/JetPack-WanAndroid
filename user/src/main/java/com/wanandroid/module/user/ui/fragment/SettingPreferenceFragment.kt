package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import androidx.preference.*
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.getStringArray
import com.wanandroid.bridge.ext.logD
import com.wanandroid.module.user.R

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @date 2020/8/3
 **/
class SettingPreferenceFragment : PreferenceFragmentCompat(),
    Preference.OnPreferenceChangeListener {

    private val preferenceColor = R.string.user_preference_color.getString()
    private val preferenceAnimation = R.string.user_preference_animation.getString()
    private val preferenceArticle = R.string.user_preference_article.getString()
    private val preferenceTag = R.string.user_preference_tag.getString()
    private val preferenceModel = R.string.user_preference_model.getString()
    private val colorArray = R.array.theme_color_entries_array.getStringArray()
    private val animationArray = R.array.rv_animation_entries_array.getStringArray()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.user_fragment_setting)
        val colorListPreference = findPreference<ListPreference>(preferenceColor)
        val animationListPreference = findPreference<ListPreference>(preferenceAnimation)
        val articlePreference = findPreference<CheckBoxPreference>(preferenceArticle)
        val tagPreference = findPreference<CheckBoxPreference>(preferenceTag)
        val modelPreference = findPreference<SwitchPreference>(preferenceModel)
        colorListPreference?.let {
            it.setDefaultValue(0)
            it.summary = colorArray[0]
        }
        animationListPreference?.let {
            it.setDefaultValue(0)
            it.summary = animationArray[0]
        }
        articlePreference?.isChecked = true
        tagPreference?.isChecked = true
        modelPreference?.isChecked = true
    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        preference ?: return super.onPreferenceTreeClick(preference)
        when (preference.key) {
            preferenceColor -> preference.onPreferenceChangeListener = this
            preferenceAnimation -> preference.onPreferenceChangeListener = this
            preferenceArticle -> "preference_article".logD()
            preferenceTag -> "preference_tag".logD()
            preferenceModel -> "preference_model".logD()
        }
        return super.onPreferenceTreeClick(preference)
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        preference ?: return false
        newValue ?: return false
        preference.setDefaultValue(newValue)
        val index = newValue.toString().toInt()
        preference.summary =
            if (preference.key == preferenceColor) colorArray[index] else animationArray[index]
        return true
    }
}