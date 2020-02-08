package com.asterisks.onesec.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asterisks.onesec.R
import com.asterisks.onesec.databinding.FragmentCrimeDescriptionBinding

class CrimeDescriptionFragment(
    val crimeReportFragment: CrimeReportFragment,
    val crimeType: Int
) : Fragment(){

    private lateinit var crimeDescriptionBinding: FragmentCrimeDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crimeDescriptionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_description, container, false)
        return crimeDescriptionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(crimeType){
            0->{
                crimeDescriptionBinding.reportTitle.text = "THEFT"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorTheft,null))
            }
            1->{
                crimeDescriptionBinding.reportTitle.text = "ASSAULT"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorAssault,null))
            }
            2->{
                crimeDescriptionBinding.reportTitle.text = "ROAD ACCIDENT"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorAccident,null))
            }
            3->{
                crimeDescriptionBinding.reportTitle.text = "DISPUTES"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorDispute,null))
            }
            4->{
                crimeDescriptionBinding.reportTitle.text = "EMERGENCY"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorEmergency,null))
            }
            else->{
                crimeDescriptionBinding.reportTitle.text = "MISCELLANEOUS"
                crimeDescriptionBinding.reportTitle.setBackgroundColor( ResourcesCompat.getColor(context!!.resources,R.color.colorMiscellaneous,null))
            }
        }
        crimeDescriptionBinding.submitReportButton.setOnClickListener {
            crimeReportFragment.ReportSend(crimeType,crimeDescriptionBinding.reportDescription.text.toString())
            activity!!.onBackPressed()
        }

    }



}