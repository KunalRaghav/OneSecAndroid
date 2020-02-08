package com.asterisks.onesec.data

data class CrimeRecord(
    val description: String,
    val priority: Int,
    val Lat: Double,
    val Long: Double,
    val type: Int,
    var isSolved: Boolean = false,
    var time: Long = 0L
)