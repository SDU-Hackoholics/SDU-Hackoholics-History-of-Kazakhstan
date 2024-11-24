package com.example.history_of_kazakhstan.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.history_of_kazakhstan.DatabaseHandler
import com.example.history_of_kazakhstan.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.usernameHead.text = ""


        return root
    }


    override fun onResume() {
        super.onResume()

        // Update the usernameHead text when the Fragment becomes visible
        binding.usernameHead.text = DatabaseHandler().getKeyValue(requireContext(), "current_user")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}