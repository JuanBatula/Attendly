package com.example.myapplicationtestthree.presenter

/**
 * HomeContract defines the MVP interfaces for the Home screen.
 */
interface HomeContract {

    interface View {
        /** Render the greeting with the user's name */
        fun showGreeting(username: String)

        /** Show an error or status message */
        fun showMessage(message: String)
    }

    interface Presenter {
        /** Called when the fragment's view is ready */
        fun loadUserData()

        /** Called in onDestroyView to prevent memory leaks */
        fun detachView()
    }
}