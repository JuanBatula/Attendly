package com.example.myapplicationtestthree.presenter

/**
 * LoginContract defines the MVP interfaces for the Login screen.
 *
 * View  — what LoginActivity can do (UI actions)
 * Presenter — what the Presenter exposes to the View
 *
 * Using an interface contract means the View and Presenter never
 * depend on each other's concrete classes, making unit testing easy.
 */
interface LoginContract {

    interface View {
        /** Show a validation or error message */
        fun showError(message: String)

        /** Navigate to MainActivity after successful login */
        fun navigateToHome()

        /** Show or hide a loading indicator */
        fun setLoadingVisible(visible: Boolean)
    }

    interface Presenter {
        /**
         * Called when the user taps Login.
         * @param username raw text from the username field
         * @param password raw text from the password field
         */
        fun onLoginClicked(username: String, password: String)

        /** Called in onDestroy to prevent memory leaks */
        fun detachView()
    }
}