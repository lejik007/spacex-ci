package com.lejik.spacex.ui.fragments.launchdetailsinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.lejik.spacex.R
import com.lejik.spacex.databinding.LaunchDetailInfoFragmentBinding
import com.lejik.spacex.model.Crew
import com.lejik.spacex.model.Docs
import com.lejik.spacex.network.ApiCrewServiceLaunches
import com.lejik.spacex.network.ApiServiceLaunches

class LaunchDetailInfoFragment : Fragment() {
    private var _binding: LaunchDetailInfoFragmentBinding? = null
    private val binding: LaunchDetailInfoFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LaunchDetailInfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 0
        var countMission = 0
        var docs: List<Docs>? = null
        var crew: List<Crew>? = null
        lifecycleScope.launchWhenResumed {
            try {
                Log.d("Отслеживание (детальная информация)", "Подключение к сайту")
                docs = ApiServiceLaunches.create().getPhotos()
                crew = ApiCrewServiceLaunches.create().getCrews()
                countMission = docs!!.size
                Log.d("Отслеживание (общая информация)", "val photos = ApiObject.retrofitService.getPhotos(): ${docs}")
                binding.apply {
                    textName.text = docs?.getOrNull(count)?.name.toString()
                    textIcon.text = docs?.getOrNull(count)?.links?.patch?.large.toString()
                    textRepeatedFlight.text = docs?.getOrNull(count)?.cores?.toString()
                    textMissionStatus.text = docs?.getOrNull(count)?.success.toString()
                    textDetails.text = docs?.getOrNull(count)?.details.toString()
                    textDateUtc.text = docs?.getOrNull(count)?.date_utc.toString()
                    imageIcon.load(
                        docs?.getOrNull(count)?.links?.patch?.large
                            .toString().toUri().buildUpon().scheme("https").build()
                    ) {
                        placeholder(R.drawable.loading_animation)
                        error(R.drawable.ic_broken_image)
                    }
                    textSpaceman.text = "https://api.spacexdata.com/v4/crew"
                    textAgency.text = "https://api.spacexdata.com/v4/crew"
                    textStatus.text = "https://api.spacexdata.com/v4/crew"
                }
            } catch (e: Exception) {
                Log.d("Отслеживание (детальная информация)", e.message.toString())
            }
        }

        binding.apply {
            backButton.setOnClickListener() {
                findNavController().navigate(R.id.action_launchDetailInfoFragment_to_launchGeneralInfoFragment)
            }

            nextButton.setOnClickListener() {
                findNavController().navigate(R.id.action_launchDetailInfoFragment_to_launchRecyclerFragment)
            }

            missionNextButton.setOnClickListener {
                if (count < countMission) {
                    count++
                }
                textName.text = docs?.getOrNull(count)?.name.toString()
                textIcon.text = docs?.getOrNull(count)?.links?.patch?.large.toString()
                textRepeatedFlight.text = docs?.getOrNull(count)?.cores?.toString()
                textMissionStatus.text = docs?.getOrNull(count)?.success.toString()
                textDetails.text = docs?.getOrNull(count)?.details.toString()
                textDateUtc.text = docs?.getOrNull(count)?.date_utc.toString()
                imageIcon.load(
                    docs?.getOrNull(count)?.links?.patch?.large
                        .toString().toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }

            missionPreviousButton.setOnClickListener {
                if (count > 0) {
                    count--
                }
                textName.text = docs?.getOrNull(count)?.name.toString()
                textIcon.text = docs?.getOrNull(count)?.links?.patch?.large.toString()
                textRepeatedFlight.text = docs?.getOrNull(count)?.cores?.toString()
                textMissionStatus.text = docs?.getOrNull(count)?.success.toString()
                textDetails.text = docs?.getOrNull(count)?.details.toString()
                textDateUtc.text = docs?.getOrNull(count)?.date_utc.toString()
                imageIcon.load(
                    docs?.getOrNull(count)?.links?.patch?.large
                        .toString().toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}