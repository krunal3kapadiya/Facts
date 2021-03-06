package app.krunal3kapadiya.facts.network.models

import com.google.gson.annotations.SerializedName

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */

data class Facts(@SerializedName("title") val title: String, @SerializedName("rows") val rowsList: List<FactsRows>)

data class FactsRows(
    @SerializedName("title") var title: String?, @SerializedName("description") var description: String?, @SerializedName(
        "imageHref"
    ) var imageHref: String?
)