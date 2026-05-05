package com.example.myapplicationtestthree

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val greetingText = view.findViewById<TextView>(R.id.tv_home_greeting)

        // SHAREDPREFERENCES: Retrieve stored data
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPref.getString("USER_NAME", "User")

        greetingText.text = "Hi, $savedName!"

        return view
    }
}