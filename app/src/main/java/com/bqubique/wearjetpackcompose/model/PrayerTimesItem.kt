package com.bqubique.wearjetpackcompose.model


import com.google.gson.annotations.SerializedName


data class PrayerTimesItem(
    @SerializedName("akshami")
    val akshami: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("dreka")
    val dreka: String,
    @SerializedName("hixhri")
    val hixhri: String,
    @SerializedName("hixhri_year")
    val hixhriYear: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ikindia")
    val ikindia: String,
    @SerializedName("imsaku")
    val imsaku: String,
    @SerializedName("jacia")
    val jacia: String,
    @SerializedName("lindja_diellit")
    val lindjaDiellit: String,
    @SerializedName("sabahu")
    val sabahu: String
)