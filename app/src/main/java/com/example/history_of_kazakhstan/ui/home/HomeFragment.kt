package com.example.history_of_kazakhstan.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.history_of_kazakhstan.databinding.FragmentHomeBinding
import org.json.JSONObject
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Read JSON from assets
        val jsonString = readJsonFromAssets(requireContext(), "quiz.json")

        // Parse JSON and get keys
        val jsonObject = JSONObject(jsonString)
        val keys = jsonObject.keys()

        // Get the layout where we want to add the buttons

        // Add buttons dynamically
        while (keys.hasNext()) {
            val key = keys.next()
            val button = Button(requireContext()).apply {
                text = key
                setOnClickListener {
                    // Handle button click
                }
            }
            binding.linearLayout.addView(button)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun readJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}