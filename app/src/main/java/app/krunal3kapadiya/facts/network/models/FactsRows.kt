package app.krunal3kapadiya.facts.network.models

import com.google.gson.annotations.SerializedName

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */

data class FactsRows(
    @SerializedName("title") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("imageHref") var imageHref: String?
)