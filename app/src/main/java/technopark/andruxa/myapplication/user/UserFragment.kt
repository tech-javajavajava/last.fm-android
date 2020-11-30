package technopark.andruxa.myapplication.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import technopark.andruxa.myapplication.R

class UserFragment : Fragment() {
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        val loginButton : Button = view.findViewById(R.id.login_button)

        viewModel.getProgress().observe(viewLifecycleOwner, UserObserver())

        val login: EditText = view.findViewById(R.id.login)
        val password: EditText = view.findViewById(R.id.password)
        loginButton.setOnClickListener {
            viewModel.login(
                login.text.toString(),
                password.text.toString()
            )
        }
    }

    private class UserObserver() :
        Observer<UserViewModel.LoginState?> {
        override fun onChanged(loginState: UserViewModel.LoginState?) {
            when {
                loginState === UserViewModel.LoginState.FAILED -> {
                }
                loginState === UserViewModel.LoginState.ERROR -> {
                }
                loginState === UserViewModel.LoginState.IN_PROGRESS -> {
                }
                loginState === UserViewModel.LoginState.SUCCESS -> {
                }
                else -> {
                }
            }
        }
    }
}