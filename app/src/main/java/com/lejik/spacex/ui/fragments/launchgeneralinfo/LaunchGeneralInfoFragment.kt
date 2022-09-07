package com.lejik.spacex.ui.fragments.launchgeneralinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import coil.load
import com.lejik.spacex.R
import com.lejik.spacex.databinding.LaunchGeneralInfoFragmentBinding
import com.lejik.spacex.model.Docs
import com.lejik.spacex.network.ApiServiceLaunches
import java.lang.Exception

class LaunchGeneralInfoFragment : Fragment() {
    private var _binding: LaunchGeneralInfoFragmentBinding? = null
    private val binding: LaunchGeneralInfoFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LaunchGeneralInfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 0
        var countMission = 0
//        var _photos = MutableLiveData<Docs>()
//        val photos: LiveData<Docs> = _photos!!
        var photos: List<Docs>? = null
        lifecycleScope.launchWhenResumed {
            try {
                //  GSON
//                Log.d("Отслеживание", "Подключение к сайту")
//                val photos = ApiObject.retrofitService.getPhotos()
//                Log.d("Отслеживание", "Получил jsonText: ${photos.getOrNull(0)?.date_utc}")
//                textGeneralInfo.text = photos.toString()

//            Moshi
                Log.d("Отслеживание (общая информация)", "Подключение к сайту")
                photos = ApiServiceLaunches.create().getPhotos() // for List<Docs>
//                _photos = ApiServiceLaunches.create().getPhotos() // for LiveData
                countMission = photos!!.size
                Log.d("Отслеживание (общая информация)", "val photos = ApiObject.retrofitService.getPhotos(): ${photos}")
//                textGeneralInfo.text = photos.toString()
                binding.apply {
                    textDateUtc.text = photos?.getOrNull(count)?.date_utc.toString()
                    textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
                    textName.text  = photos?.getOrNull(count)?.name.toString()
                    textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
                    textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
                    imageIcon.load(
                        photos?.getOrNull(count)?.links?.patch?.small
                            .toString().toUri().buildUpon().scheme("https").build()
                    ) {
                        placeholder(R.drawable.loading_animation)
                        error(R.drawable.ic_broken_image)
                    }
                }

//                Picasso.get()
//                    .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
//                    .into(imageIcon);
//                imageIcon.setImageURI(Uri.parse(photos?.getOrNull(count)?.links?.patch?.small.toString()))
            } catch (e: Exception) {
                Log.d("Отслеживание (общая информация)", e.message.toString())
            }
        }
/*  Без корутин
        val request = ApiObject.retrofitService.getPhotos()
        request.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                textGeneralInfo.text = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })*/
        val navController = NavHostFragment.findNavController(this)
        binding.apply {
            nextButton.setOnClickListener() {
                navController.navigate(R.id.action_launchGeneralInfoFragment_to_launchDetailInfoFragment)
            }
            imageIcon.setOnClickListener() {
                navController.navigate(R.id.action_launchGeneralInfoFragment_to_launchDetailInfoFragment)
            }
            missionNextButton.setOnClickListener() {
                if (count < countMission) {
                    count++
                }
                textDateUtc.text = photos?.getOrNull(count)?.date_utc
                textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
                textName.text  = photos?.getOrNull(count)?.name.toString()
                textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
                textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
                imageIcon.load(
                    photos?.getOrNull(count)?.links?.patch?.small
                        .toString().toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
//            Picasso.get()
//                .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
//                .into(imageIcon);
//            imageIcon.setImageURI(Uri.parse(photos?.getOrNull(count)?.links?.patch?.small.toString()))
            }
            missionPreviousButton.setOnClickListener() {
                if (count > 0) {
                    count--
                }
                textDateUtc.text = photos?.getOrNull(count)?.date_utc
                textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
                textName.text  = photos?.getOrNull(count)?.name.toString()
                textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
                textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
                imageIcon.load(
                    photos?.getOrNull(count)?.links?.patch?.small
                        .toString().toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
//            Picasso.get()
//                .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
//                .into(imageIcon);
//            imageIcon.setImageURI(Uri.parse(photos?.getOrNull(count)?.links?.patch?.small.toString()))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}