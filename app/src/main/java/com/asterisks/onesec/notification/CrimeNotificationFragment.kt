package com.asterisks.onesec.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asterisks.onesec.R
import com.asterisks.onesec.databinding.FragmentCrimeNotificationBinding
import com.google.firebase.database.*


class CrimeNotificationFragment : Fragment(){

    lateinit var crimeNotificationBinding: FragmentCrimeNotificationBinding
    private lateinit var databaseRef: DatabaseReference
    lateinit var alertAdapter: AlertAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crimeNotificationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_notification, container, false)
        databaseRef = FirebaseDatabase.getInstance().getReference("alerts")
        return crimeNotificationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alertAdapter = AlertAdapter()
        crimeNotificationBinding.recyclerAlerts.adapter = alertAdapter
        crimeNotificationBinding.recyclerAlerts.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val alertList= ArrayList<String>()
//        databaseRef.addListenerForSingleValueEvent(
//            object : ValueEventListener{
//                override fun onCancelled(p0: DatabaseError) {
//                }
//
//                override fun onDataChange(p0: DataSnapshot) {
//                    for( i in p0.children){
//                        alertList.add(i.value.toString())
//                    }
//                }
//            }
//        )
//        alertAdapter.passAlerts(alertList)
//        alertAdapter.notifyDataSetChanged()
        databaseRef.addChildEventListener(
            object: ChildEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    alertList.add(0,p0.value.toString())
                    alertAdapter.passAlerts(alertList)
                    alertAdapter.notifyItemInserted(alertList.size-1)

                }

                override fun onChildRemoved(p0: DataSnapshot) {
                }
            }
        )
    }
}