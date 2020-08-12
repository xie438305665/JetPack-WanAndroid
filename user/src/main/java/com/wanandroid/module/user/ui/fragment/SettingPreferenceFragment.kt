package com.wanandroid.module.user.ui.fragment

import android.os.Bundle
import androidx.preference.*
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.annotation.EventBusKey
import com.wanandroid.bridge.base.appContext
import com.wanandroid.bridge.event.EventEntity
import com.wanandroid.bridge.ext.getString
import com.wanandroid.bridge.ext.getStringArray
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
    private lateinit var config: AppConfig

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        if (appContext.configEvent.configVm.value == null) config =
            AppConfig() else appContext.configEvent.configVm.value?.data
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
                if (config.themeColor > colorArray.size) 0 else config.themeColor
            it.setDefaultValue(index)
            it.summary = colorArray[index]
            it.setValueIndex(index)
            it.onPreferenceChangeListener = this
        }
        findPreference<ListPreference>(preferenceAnimation)?.let {
            val index = config.animation
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
                if (preference.key == preferenceColor) config.themeColor =
                    index else config.animation = index
            }
            preferenceArticle -> config.showTop = newValue as Boolean
            preferenceTag -> config.showTag = newValue as Boolean
            else -> config.model = newValue as Boolean
        }
        appContext.configEvent.configVm.value = EventEntity(
            EventBusKey.CONFIG,
            config
        )
        return true
    }
}