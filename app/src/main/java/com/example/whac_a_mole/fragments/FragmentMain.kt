package com.example.whac_a_mole.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.whac_a_mole.MainActivity
import com.example.whac_a_mole.R
import com.example.whac_a_mole.databinding.FragmentMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding: FragmentMainBinding
        get() = _binding!!

    private val mainActivity by lazy {
        activity as MainActivity
    }

    private val rulesDialog by lazy {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.rule_title)
            .setMessage(R.string.rules)
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val view = binding.root
        binding.btnPlay.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_fragmentGame)
        }

        binding.ivRules.setOnClickListener {
            rulesDialog.show()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        if (mainActivity.highScore >= 0) {
            binding.tvHighScore.text = getString(R.string.high_score, mainActivity.highScore)
        } else {
            binding.tvHighScore.isVisible = false
        }
    }

}