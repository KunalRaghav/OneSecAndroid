package com.asterisks.onesec.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asterisks.onesec.R
import com.asterisks.onesec.databinding.FragmentCrimeMapBinding
import com.google.android.gms.common.util.DataUtils


class CrimeMapFragment : Fragment() {


    lateinit var crimeMapBinding: FragmentCrimeMapBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crimeMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_map,container,false)
        crimeMapBinding.mapWebView.settings.javaScriptEnabled = true
        crimeMapBinding.mapWebView.loadUrl("https://angry-visvesvaraya-7c9c41.netlify.com/")
        return crimeMapBinding.root
    }
}
