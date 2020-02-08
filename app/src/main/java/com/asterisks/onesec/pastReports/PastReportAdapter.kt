package com.asterisks.onesec.pastReports

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.asterisks.onesec.R
import com.asterisks.onesec.data.CrimeRecord
import com.asterisks.onesec.databinding.ItemPastReportBinding
import java.text.SimpleDateFormat
import java.util.*

class PastReportAdapter(val context: Context) : RecyclerView.Adapter<PastReportAdapter.PastReportViewHolder>() {

    var pastCrimeReports: List<CrimeRecord>? = null
    val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm")
    lateinit var pastReportBinding: ItemPastReportBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        pastReportBinding = ItemPastReportBinding.inflate(inflater, parent, false)
        return PastReportViewHolder(pastReportBinding)
    }

    inner class PastReportViewHolder(val binding: ItemPastReportBinding): RecyclerView.ViewHolder(binding.root){}

    override fun getItemCount(): Int {
        return pastCrimeReports?.size?:0
    }

    fun passReports(reports: List<CrimeRecord>){
        pastCrimeReports = reports
    }

    override fun onBindViewHolder(holder: PastReportViewHolder, position: Int) {
        val crimeRecord = pastCrimeReports!![position]
        holder.binding.pastReportDescription.text = crimeRecord.description
        when(crimeRecord.type){
            0-> {
                holder.binding.reportTypeTextView.text = "Theft"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorTheft,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorTheft,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
            1-> {
                holder.binding.reportTypeTextView.text = "Assault"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorAssault,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorAssault,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
            2-> {
                holder.binding.reportTypeTextView.text = "Road Accident"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorAccident,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorAccident,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
            3-> {
                holder.binding.reportTypeTextView.text = "Dispute"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorDispute,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorDispute,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
            4-> {
                holder.binding.reportTypeTextView.text = "SOS"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorEmergency,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorEmergency,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
            else -> {
                holder.binding.reportTypeTextView.text = "Miscellaneous"
                holder.binding.pastReportCard.setStrokeColor(ResourcesCompat.getColor(context.resources,R.color.colorMiscellaneous,null))
                holder.binding.colorLabel.setBackgroundColor( ResourcesCompat.getColor(context.resources,R.color.colorMiscellaneous,null))
                holder.binding.timeLabel.text =simpleDateFormat.format( Date(crimeRecord.time) )
            }
        }


    }
}

