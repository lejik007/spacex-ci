package com.lejik.spacex.ui.fragments.launchgeneralinfo

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import coil.load
import com.lejik.spacex.R
import com.lejik.spacex.databinding.LaunchGeneralInfoFragmentBinding
import com.lejik.spacex.databinding.LaunchRecyclerFragmentBinding
import com.lejik.spacex.model.Docs
import com.lejik.spacex.network.ApiService
import com.squareup.picasso.Picasso
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 0
        var countMission = 0
        var photos: List<Docs>? = null
        lifecycleScope.launchWhenResumed {
            try {
                //  GSON
//                Log.d("Отслеживание", "Подключение к сайту")
//                val photos = ApiObject.retrofitService.getPhotos()
//                Log.d("Отслеживание", "Получил jsonText: ${photos.getOrNull(0)?.date_utc}")
//                textGeneralInfo.text = photos.toString()

//            Moshi
                Log.d("Отслеживание", "Подключение к сайту")
                photos = ApiService.create().getPhotos()
                countMission = photos!!.size
                Log.d("Отслеживание", "val photos = ApiObject.retrofitService.getPhotos(): ${photos}")
//                textGeneralInfo.text = photos.toString()
                binding.textDateUtc.text = photos?.getOrNull(count)?.date_utc
                binding.textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
                binding.textName.text  = photos?.getOrNull(count)?.name.toString()
                binding.textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
                binding.textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
                binding.imageIcon.load(
                    photos?.getOrNull(count)?.links?.patch?.small
                        .toString().toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
//                Picasso.get()
//                    .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
//                    .into(imageIcon);
//                imageIcon.setImageURI(Uri.parse(photos?.getOrNull(count)?.links?.patch?.small.toString()))
            } catch (e: Exception) {
                Log.d("Отслеживание", e.message.toString())
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
        binding.nextButton.setOnClickListener() {
            navController.navigate(R.id.action_launchGeneralInfoFragment_to_launchDetailInfoFragment)
        }
        binding.imageIcon.setOnClickListener() {
            navController.navigate(R.id.action_launchGeneralInfoFragment_to_launchDetailInfoFragment)
        }
        binding.missionNextButton.setOnClickListener() {
            if (count < countMission) {
                count++
            }
            binding.textDateUtc.text = photos?.getOrNull(count)?.date_utc
            binding.textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
            binding.textName.text  = photos?.getOrNull(count)?.name.toString()
            binding.textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
            binding.textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
            binding.imageIcon.load(
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
        binding.missionPreviousButton.setOnClickListener() {
            if (count > 0) {
                count--
            }
            binding.textDateUtc.text = photos?.getOrNull(count)?.date_utc
            binding.textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
            binding.textName.text  = photos?.getOrNull(count)?.name.toString()
            binding.textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
            binding.textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
            binding.imageIcon.load(
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