package com.example.myapplicationtestthree.presenter

import com.example.myapplicationtestthree.data.repository.UserRepository

/**
 * LoginPresenter — Presenter layer for the Login screen.
 *
 * Contains ALL business logic for login validation and persistence.
 * Has no Android imports — fully unit-testable without a device.
 */
class LoginPresenter(
    private var view: LoginContract.View?,
    private val userRepository: UserRepository
) : LoginContract.Presenter {

    override fun onLoginClicked(username: String, password: String) {
        // Validation logic lives here, NOT in the Activity
        if (username.isEmpty() || password.isEmpty()) {
            view?.showError("Please enter both username and password")
            return
        }

        if (username.length < 3) {
            view?.showError("Username must be at least 3 characters")
            return
        }

        if (password.length < 4) {
            view?.showError("Password must be at least 4 characters")
            return
        }

        // Persist user data via the Repository (Model)
        view?.setLoadingVisible(true)
        userRepository.saveUser(username)
        view?.setLoadingVisible(false)

        // Tell the View to navigate — presenter does NOT create Intents
        view?.navigateToHome()
    }

    override fun detachView() {
        // Null out the view reference to avoid memory leaks
        view = null
    }
}