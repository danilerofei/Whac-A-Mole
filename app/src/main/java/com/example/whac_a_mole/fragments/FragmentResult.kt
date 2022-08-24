package com.example.whac_a_mole.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.whac_a_mole.MainActivity
import com.example.whac_a_mole.R
import com.example.whac_a_mole.databinding.FragmentResultBinding

class FragmentResult : Fragment() {

    private var _binding: FragmentResultBinding? = null

    private val binding: FragmentResultBinding
        get() = _binding!!

    private val mainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnBackMenu.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentResult_to_fragmentMain)
        }

        binding.btnReplay.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentResult_to_fragmentGame)
        }

        if (mainActivity.gameScore >= mainActivity.highScore) {
            mainActivity.highScore = mainActivity.gameScore
        }

        binding.tvScore.text = getString(R.string.score, mainActivity.gameScore)
        binding.tvHighScore.text = getString(R.string.high_score, mainActivity.highScore)

        return view
    }
}