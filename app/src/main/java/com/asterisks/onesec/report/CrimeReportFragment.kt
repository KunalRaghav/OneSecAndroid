
package com.asterisks.onesec.report

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asterisks.onesec.R
import com.asterisks.onesec.data.CrimeRecord
import com.asterisks.onesec.databinding.FragmentCrimeReportBinding
import com.google.android.gms.location.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CrimeReportFragment : Fragment() {

    lateinit var crimeReportBinding: FragmentCrimeReportBinding
    private lateinit var database: DatabaseReference
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var Lat:Double = 0.0
    var Long:Double = 0.0
    private var UPDATE_INTERVAL = 10*1000L
    private var FASTEST_INTERVAL = 2000L
    private lateinit var locationRequest: LocationRequest
    private val TAG = "CrimeReportFragment"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        startLocationUpdates()
        crimeReportBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_report, container, false)
        crimeReportBinding.cardTheft.setOnClickListener { taskOpenCrimeDescription(0) }
        crimeReportBinding.cardAssault.setOnClickListener { taskOpenCrimeDescription( 1)  }
        crimeReportBinding.cardRoadAccident.setOnClickListener { taskOpenCrimeDescription( 2)  }
        crimeReportBinding.cardDisputes.setOnClickListener { taskOpenCrimeDescription( 3)  }
        crimeReportBinding.cardSOS.setOnClickListener { taskOpenCrimeDescription( 4)  }
        crimeReportBinding.cardMisc.setOnClickListener { taskOpenCrimeDescription( -1)  }

        crimeReportBinding.cardSOS.setOnLongClickListener (
            object : View.OnLongClickListener{
                override fun onLongClick(v: View?): Boolean {
                    ReportSend(4, null)
                    Toast.makeText(context, "SENDING AN SOS MESSAGE", Toast.LENGTH_LONG).show()
                    return true
                }
            }
        )
        return crimeReportBinding.root
    }

    fun taskOpenCrimeDescription(crimeType: Int){
        activity!!.supportFragmentManager.beginTransaction()
            .add(R.id.container,CrimeDescriptionFragment(this, crimeType), CrimeDescriptionFragment::class.java.simpleName)
            .addToBackStack(CrimeDescriptionFragment::class.java.simpleName)
            .commit()
    }

    fun ReportSend(crimeType: Int, crimeDescription: String?){
        var crimeRecord: CrimeRecord
        var crimeTypeString: String
        database = FirebaseDatabase.getInstance().reference
        if(crimeDescription.isNullOrEmpty()) {
            when (crimeType) {
                0 -> {
                    crimeRecord = CrimeRecord(
                        "Robbery/Theft goods stolen must be recovered at priority",
                        0,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Theft"
                }
                1 -> {
                    crimeRecord = CrimeRecord(
                        "This person was assaulted, by some people while walking down this road",
                        1,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Assault"
                }

                2 -> {
                    crimeRecord = CrimeRecord(
                        "This person has reported a road accident at the specified location",
                        2,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "RoadAccident"
                }
                3 -> {
                    crimeRecord = CrimeRecord(
                        "This person was caught in a dispute",
                        3,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Dispute"
                }
                4 -> {
                    crimeRecord = CrimeRecord(
                        "This person has sent an SOS message",
                        4,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "SOS"
                }
                else -> {
                    crimeRecord = CrimeRecord(
                        "Example of a miscellaneous report",
                        -1,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Miscellaneous"
                }
            }
        }else{
            when (crimeType) {
                0 -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        0,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Theft"
                }
                1 -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        1,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Assault"
                }

                2 -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        2,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "RoadAccident"
                }
                3 -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        3,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Dispute"
                }
                4 -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        4,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "SOS"
                }
                else -> {
                    crimeRecord = CrimeRecord(
                        crimeDescription,
                        -1,
                        Lat,
                        Long,
                        crimeType,
                        false,
                        System.currentTimeMillis()
                    )
                    crimeTypeString = "Miscellaneous"
                }
            }
        }
        database.child("reports").child(crimeTypeString)
            .child("${System.currentTimeMillis()}")
            .setValue(crimeRecord)
    }

    fun startLocationUpdates(){
        locationRequest = LocationRequest().also {
            it.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            it.setInterval(UPDATE_INTERVAL)
            it.setFastestInterval(FASTEST_INTERVAL)
        }
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(context!!)
        settingsClient.checkLocationSettings(locationSettingsRequest)


        FusedLocationProviderClient(context!!).requestLocationUpdates(
            locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) { // do work here
                    onLocationChanged(locationResult.lastLocation)
                }
            },
            Looper.myLooper()
        )

    }


    fun onLocationChanged(lastLocation: Location){
        val msg = "Updated Location: " +
                java.lang.Double.toString(lastLocation.latitude) + "," +
                java.lang.Double.toString(lastLocation.longitude)
        Log.d(TAG,msg)
      //  Toast.makeText(activity!!, msg, Toast.LENGTH_SHORT).show()
        Lat = lastLocation.latitude
        Long = lastLocation.longitude
    }
}
