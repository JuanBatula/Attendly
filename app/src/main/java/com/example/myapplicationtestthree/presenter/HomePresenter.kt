package com.example.myapplicationtestthree.presenter

import com.example.myapplicationtestthree.data.repository.UserRepository

/**
 * HomePresenter — Presenter layer for HomeFragment.
 *
 * Fetches user data from the Repository and tells the View what to display.
 * No Android framework imports — pure Kotlin, fully unit-testable.
 */
class HomePresenter(
    private var view: HomeContract.View?,
    private val userRepository: UserRepository
) : HomeContract.Presenter {

    override fun loadUserData() {
        val username = userRepository.getUsername()

        if (username.isNotEmpty()) {
            view?.showGreeting(username)
        } else {
            view?.showGreeting("User")
        }
    }

    override fun detachView() {
        view = null
    }
}