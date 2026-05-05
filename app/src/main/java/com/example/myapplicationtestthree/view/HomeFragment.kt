package com.example.myapplicationtestthree.view

import com.example.myapplicationtestthree.AttendanceApplication
import com.example.myapplicationtestthree.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplicationtestthree.extensions.showToast
import com.example.myapplicationtestthree.presenter.HomeContract
import com.example.myapplicationtestthree.presenter.HomePresenter

/**
 * HomeFragment — View layer in MVP.
 *
 * BEFORE: directly read SharedPreferences to populate the greeting TextView.
 * AFTER:  implements HomeContract.View; a Presenter drives all data decisions.
 */
class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var greetingText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        greetingText = view.findViewById(R.id.tv_home_greeting)

        // Retrieve the shared repository from the Application class
        val repository = (requireActivity().application as AttendanceApplication).userRepository

        // Presenter is created here; 'this' is the View
        presenter = HomePresenter(this, repository)

        // Ask presenter to load user data — no SharedPreferences access in the Fragment
        presenter.loadUserData()
    }

    // --- HomeContract.View implementations ---

    override fun showGreeting(username: String) {
        greetingText.text = "Hi, $username!"
    }

    override fun showMessage(message: String) {
        requireContext().showToast(message) // ← correct
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Prevent memory leak: null out the view reference in the presenter
        presenter.detachView()
    }
}