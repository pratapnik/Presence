package com.example.presence.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.presence.R

class HomeFragment : Fragment() {

    lateinit var btnSettings : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnSettings = view.findViewById(R.id.btnSettings)
        btnSettings.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        return view
    }

}
