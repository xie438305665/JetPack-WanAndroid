package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import androidx.preference.*
import com.wanandroid.bridge.annotation.EventBusTag
import com.wanandroid.bridge.base.EventBusEntity
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.getStringArray
import com.wanandroid.bridge.ext.postEvent
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
        findPreference<CheckBoxPreference>(preferenceArticle)?.let {
            it.isChecked = true
            it.onPreferenceChangeListener = this
        }
        findPreference<CheckBoxPreference>(preferenceTag)?.let {
            it.isChecked = true
            it.onPreferenceChangeListener = this
        }
        findPreference<SwitchPreference>(preferenceModel)?.let {
            it.isChecked = true
            it.onPreferenceChangeListener = this
        }
        findPreference<ListPreference>(preferenceColor)?.let {
            val index =
                if (appContext.config.themeColor > colorArray.size) 0 else appContext.config.themeColor
            it.setDefaultValue(index)
            it.summary = colorArray[index]
            it.setValueIndex(index)
            it.onPreferenceChangeListener = this
        }
        findPreference<ListPreference>(preferenceAnimation)?.let {
            val index = appContext.config.animation
            it.setDefaultValue(index)
            it.setValueIndex(index)
            it.summary = animationArray[index]
            it.onPreferenceChangeListener = this
        }
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        preference ?: return false
        newValue ?: return false
        when (preference.key) {
            preferenceColor, preferenceAnimation -> {
                val index = newValue.toString().toInt()
                preference.setDefaultValue(newValue)
                preference.summary =
                    if (preference.key == preferenceColor) colorArray[index] else animationArray[index]
                if (preference.key == preferenceColor) appContext.config.themeColor =
                    index else appContext.config.animation = index
            }
            preferenceArticle -> appContext.config.showTop = newValue as Boolean
            preferenceTag -> appContext.config.showTag = newValue as Boolean
            else -> appContext.config.model = newValue as Boolean
        }
        postEvent(EventBusTag.CONFIG, EventBusEntity(EventBusTag.CONFIG, appContext.config))
        return true
    }
}