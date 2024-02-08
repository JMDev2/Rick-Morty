package com.ekenya.rnd.common.utils

import android.content.Context

object SHaredPreference {

    // Retrieve the selected character ID
    fun getSelectedCharacterId(context: Context): Int {
        return context.getSharedPreferences("characters", Context.MODE_PRIVATE)
            .getInt("selectedCharacterId", -1)
    }

    // Save the selected character ID
    fun setSelectedCharacterId(context: Context, selectedCharacterId: Int) {
        context.getSharedPreferences("characters", Context.MODE_PRIVATE).edit()
            .putInt("selectedCharacterId", selectedCharacterId).apply()
    }
}