package com.example.whac_a_mole.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.whac_a_mole.MainActivity
import com.example.whac_a_mole.R
import com.example.whac_a_mole.data.HoleData
import com.example.whac_a_mole.databinding.FragmentGameBinding
import kotlin.random.Random

class FragmentGame : Fragment() {

    private var _binding: FragmentGameBinding? = null

    private val binding: FragmentGameBinding
        get() = _binding!!

    private val mainActivity by lazy {
        activity as MainActivity
    }

    private val holes by lazy {
        listOf(
            binding.ivHole1,
            binding.ivHole2,
            binding.ivHole3,
            binding.ivHole4,
            binding.ivHole5,
            binding.ivHole6,
            binding.ivHole7,
            binding.ivHole8,
            binding.ivHole9,
        )
    }

    private val holeList by lazy {
        listOf(
            HoleData(holes[0], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[1], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[2], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[3], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[4], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[5], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[6], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[7], R.drawable.mole_anim_0, R.drawable.mole_animation),
            HoleData(holes[8], R.drawable.mole_anim_0, R.drawable.mole_animation)
        )
    }

    private val gameTimer by lazy {
        object : CountDownTimer(30_000L, HoleData.DEFAULT_TIME) {
            override fun onTick(millisUntilFinished: Long) {
                for (hole in holeList) {
                    hole.isActive = false
                }
                val randHole = Random.nextInt(0, 8)
                binding.tvScore.text = getString(R.string.score, mainActivity.gameScore)
                holeList[randHole].isActive = true

                drawHoleInfo()

                binding.tvTimer.text = (millisUntilFinished / 1_000L).toString()
            }

            override fun onFinish() {
                val view = binding.root
                Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentGame_to_fragmentResult)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        for (holeId in holes.indices) {
            holes[holeId].setOnClickListener {
                if (holeList[holeId].isActive) {
                    mainActivity.gameScore += 1
                    holeList[holeId].isActive = false
                    holes[holeId].tag = "kick"
                    drawHoleInfo()
                }
            }

        }

        return binding.root
    }

    private fun drawHoleInfo() {
        for (holeId in holes.indices) {
            val view = holes[holeId]
            when (view.tag) {
                null, R.drawable.mole_anim_0, R.drawable.mole_reverse_anim -> {
                    val drawable: Int = holeList[holeId].getDrawable()
                    view.setImageResource(drawable)
                    view.tag = drawable
                }
                R.drawable.mole_animation -> {
                    view.setImageResource(R.drawable.mole_reverse_anim)
                    view.tag = R.drawable.mole_reverse_anim
                }
                "kick" -> {
                    view.setImageResource(R.drawable.mole_kick_anim)
                    view.tag = null
                }
            }
            val anim = view.drawable as? AnimationDrawable
            anim?.start()
        }
    }

    override fun onStart() {
        super.onStart()
        gameTimer.start()
        mainActivity.gameScore = 0
        drawHoleInfo()
    }
}