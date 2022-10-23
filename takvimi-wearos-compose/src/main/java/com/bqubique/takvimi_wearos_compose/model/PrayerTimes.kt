package com.bqubique.takvimi_wearos_compose.model


import com.google.gson.annotations.SerializedName


data class PrayerTimes(
    @SerializedName("akshami")
    val akshami: String,
    @SerializedName("dataG")
    val dataG: String,
    @SerializedName("dita")
    val dita: String,
    @SerializedName("dreka")
    val dreka: String,
    @SerializedName("GjDites")
    val gjDites: String,
    @SerializedName("hixhri")
    val hixhri: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ikindia")
    val ikindia: String,
    @SerializedName("imsaku")
    val imsaku: String,
    @SerializedName("jacia")
    val jacia: String,
    @SerializedName("LDiellit")
    val lDiellit: String,
    @SerializedName("shenime")
    val shenime: String
)