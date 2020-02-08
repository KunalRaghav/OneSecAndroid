package com.asterisks.onesec.pastReports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asterisks.onesec.R
import com.asterisks.onesec.data.CrimeRecord
import com.asterisks.onesec.databinding.FragmentPastReportsBinding
import com.google.firebase.database.*

class PastReportsFragment : Fragment(){


    private lateinit var pastReportBinding: FragmentPastReportsBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var  pastReportAdapter: PastReportAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pastReportBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_past_reports, container , false)

        return pastReportBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseRef = FirebaseDatabase.getInstance().getReference("reports")
        val crimeRecords = ArrayList<CrimeRecord>()
        pastReportAdapter = PastReportAdapter(context!!)
        pastReportBinding.recyclerPastReports.adapter = pastReportAdapter
        pastReportBinding.recyclerPastReports.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        databaseRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                pastReportBinding.assaultCountTextView.text= p0.child("Assault").childrenCount.toString()
                pastReportBinding.theftCountTextView.text= p0.child("Theft").childrenCount.toString()
                pastReportBinding.accidentCountTextView.text= p0.child("RoadAccident").childrenCount.toString()
                pastReportBinding.disputeCountTextView.text= p0.child("Dispute").childrenCount.toString()
                for( i in p0.child("Assault").children){
                    val crimeRecord = CrimeRecord(
                        i.child("description").value.toString(),
                        i.child("priority").value.toString().toInt(),
                        i.child("lat").value.toString().toDouble(),
                        i.child("long").value.toString().toDouble(),
                        i.child("type").value.toString().toInt(),
                        i.child("isSolved").value.toString().toBoolean(),
                        i.child("time").value.toString().toLong()?:0
                    )
                    crimeRecords.add(crimeRecord)
                }
                for( i in p0.child("Theft").children){
                    val crimeRecord = CrimeRecord(
                        i.child("description").value.toString(),
                        i.child("priority").value.toString().toInt(),
                        i.child("lat").value.toString().toDouble(),
                        i.child("long").value.toString().toDouble(),
                        i.child("type").value.toString().toInt(),
                        i.child("isSolved").value.toString().toBoolean(),
                        i.child("time").value.toString().toLong()?:0
                    )
                    crimeRecords.add(crimeRecord)
                }
                for( i in p0.child("RoadAccident").children){
                    val crimeRecord = CrimeRecord(
                        i.child("description").value.toString(),
                        i.child("priority").value.toString().toInt(),
                        i.child("lat").value.toString().toDouble(),
                        i.child("long").value.toString().toDouble(),
                        i.child("type").value.toString().toInt(),
                        i.child("isSolved").value.toString().toBoolean(),
                        i.child("time").value.toString().toLong()?:0
                    )
                    crimeRecords.add(crimeRecord)
                }
                for( i in p0.child("Dispute").children){
                    val crimeRecord = CrimeRecord(
                        i.child("description").value.toString(),
                        i.child("priority").value.toString().toInt(),
                        i.child("lat").value.toString().toDouble(),
                        i.child("long").value.toString().toDouble(),
                        i.child("type").value.toString().toInt(),
                        i.child("isSolved").value.toString().toBoolean(),
                        i.child("time").value.toString().toLong()?:0
                    )
                    crimeRecords.add(crimeRecord)
                }
                for( i in p0.child("Miscellaneous").children){
                    val crimeRecord = CrimeRecord(
                        i.child("description").value.toString(),
                        i.child("priority").value.toString().toInt(),
                        i.child("lat").value.toString().toDouble(),
                        i.child("long").value.toString().toDouble(),
                        i.child("type").value.toString().toInt(),
                        i.child("isSolved").value.toString().toBoolean(),
                        i.child("time").value.toString().toLong()?:0
                    )
                    crimeRecords.add(crimeRecord)
                }

                crimeRecords.sortByDescending {
                    it.time
                }
                pastReportAdapter.passReports(crimeRecords)
                pastReportAdapter.notifyDataSetChanged()




            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}