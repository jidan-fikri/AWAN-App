package com.example.awan.ui.utils

object dataDummy {
    fun generateDummySkins(): List<skinsEntity>{
        val skins = ArrayList<skinsEntity>()
        skins.add(skinsEntity(
            "Heavy_Rain ",
            "Today's weather forecast shows that it will likely rain a lot It is highly recommended to provide an umbrella or raincoat when outdoor activities and be careful when driving"
            ,   "The cloud types for this condition are cumulonimbus and cumulus."
        ))
        skins.add(skinsEntity(
            "Low_Chance_of_Rain",
            "Today's weather forecast shows that it will rain moderately. Outdoor activities are still possible to do. However, it is still advisable to always bring an umbrella or raincoat for unexpected weather changes."
            ,   "Cloud types for this condition are cirrus, cirrostratus, cirrocumulus, and altocumulus."
        ))
        skins.add(skinsEntity(
            "Medium_Rain",
            "Today's forecast is likely to show moderate rain. Outdoor activities are still possible to do. It is recommended to provide an umbrella or raincoat when outdoor activities and be careful when driving."
            ,   "Cloud types for this condition are altostratus, stratocumulus, stratus, and nimbostratus."
        ))

        return skins
    }

}