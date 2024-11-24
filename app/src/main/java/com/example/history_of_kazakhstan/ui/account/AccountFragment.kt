package com.example.history_of_kazakhstan.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.Data
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.usernameHead.text = ""
        binding.accountCreated.text = ""
        binding.lastEnter.text = ""


        return root
    }


    override fun onResume() {
        super.onResume()

        // Update the usernameHead text when the Fragment becomes visible
        val username = DatabaseHandler().getKeyValue(requireContext(), "current_user").toString()

        val accountCreated = DatabaseHandler().transformDateString(DatabaseHandler().getUserKeyValue(requireContext(), username, "account_created").toString())
        val lastEnter = DatabaseHandler().transformDateString(DatabaseHandler().getUserKeyValue(requireContext(), username, "last_enter").toString())

        binding.usernameHead.text = username
        binding.accountCreated.text = "Account created:\n\t$accountCreated"
        binding.lastEnter.text = "Last entered:\n\t$lastEnter"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}