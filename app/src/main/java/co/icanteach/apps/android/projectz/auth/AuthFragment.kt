package co.icanteach.apps.android.projectz.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import co.icanteach.apps.android.projectz.R
import co.icanteach.apps.android.projectz.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAuthBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.authButton.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            authViewModel.auth(
                email = email,
                password = password
            )
        }

        authViewModel.pageErrorState.observe(viewLifecycleOwner, Observer { errorViewState ->
            binding.authPageErrorViewState = errorViewState
            binding.executePendingBindings()
        })
        return binding.root
    }
}