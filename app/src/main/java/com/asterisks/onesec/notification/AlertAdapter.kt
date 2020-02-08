package com.asterisks.onesec.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisks.onesec.R
import com.asterisks.onesec.databinding.ItemAlertBinding

class AlertAdapter() : RecyclerView.Adapter<AlertAdapter.AlertViewHolder>(){

    var alerts: List<String>? = null
    lateinit var alertBinding: ItemAlertBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        alertBinding = DataBindingUtil.inflate(inflater, R.layout.item_alert,parent,false)
        return AlertViewHolder(alertBinding)
    }

    override fun getItemCount(): Int {
        return alerts?.size?:0
    }

    fun passAlerts(newAlertList: List<String>){
        alerts = newAlertList
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.binding.alertText.text = alerts!![position]
    }

    inner class AlertViewHolder (val binding: ItemAlertBinding): RecyclerView.ViewHolder(binding.root)
}