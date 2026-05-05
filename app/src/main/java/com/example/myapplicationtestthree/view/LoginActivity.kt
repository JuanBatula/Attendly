package com.example.myapplicationtestthree.view

import com.example.myapplicationtestthree.AttendanceApplication
import com.example.myapplicationtestthree.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationtestthree.extensions.showToast
import com.example.myapplicationtestthree.presenter.LoginContract
import com.example.myapplicationtestthree.presenter.LoginPresenter

/**
 * LoginActivity — View layer in MVP.
 *
 * BEFORE: contained login validation logic and direct SharedPreferences calls.
 * AFTER:  delegates all logic to LoginPresenter; only handles UI interactions.
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        // Retrieve repository from the Application class (no new instantiation here)
        val repository = (application as AttendanceApplication).userRepository

        // Create presenter, injecting view (this) and the repository
        presenter = LoginPresenter(this, repository)

        usernameInput = findViewById(R.id.edit_username)
        passwordInput = findViewById(R.id.edit_password)
        loginButton   = findViewById(R.id.btn_login)

        // ProgressBar can be added to login_screen.xml; safe to skip if not added yet
        progressBar = ProgressBar(this) // placeholder — replace with layout reference

        loginButton.setOnClickListener {
            // View only collects input and delegates to presenter
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            presenter.onLoginClicked(username, password)
        }
    }

    // --- LoginContract.View implementations ---

    override fun showError(message: String) {
        // Extension function replaces Toast.makeText(...).show()
        showToast(message)
    }

    override fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setLoadingVisible(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detach view to prevent memory leak
        presenter.detachView()
    }
}