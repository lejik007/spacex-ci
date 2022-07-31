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
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.lejik.spacex.R
import com.lejik.spacex.model.Docs
import com.lejik.spacex.network.ApiObject
import com.squareup.picasso.Picasso
import java.lang.Exception

class LaunchGeneralInfoFragment : Fragment() {
    private lateinit var nextButton: Button
    private lateinit var textGeneralInfo: TextView
    private lateinit var missionNextButton: Button
    private lateinit var missionPreviousButton: Button
    private lateinit var textDateUtc: TextView
    private lateinit var textIcon: TextView
    private lateinit var textName: TextView
    private lateinit var textRepeatedFlight: TextView
    private lateinit var textMissionStatus: TextView
    private lateinit var imageIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.launch_general_info_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton = view.findViewById(R.id.nextButton)
//        textGeneralInfo = view.findViewById(R.id.textGeneralInfo)
        missionNextButton = view.findViewById(R.id.missionNextButton)
        missionPreviousButton = view.findViewById(R.id.missionPreviousButton)
        textDateUtc = view.findViewById(R.id.textDateUtc)
        textIcon  = view.findViewById(R.id.textIcon)
        textName  = view.findViewById(R.id.textName)
        textRepeatedFlight = view.findViewById(R.id.textRepeatedFlight)
        textMissionStatus = view.findViewById(R.id.textMissionStatus)
        imageIcon = view.findViewById(R.id.imageIcon)
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
                photos = ApiObject.retrofitService.getPhotos()
                countMission = photos!!.size
                Log.d("Отслеживание", "val photos = ApiObject.retrofitService.getPhotos(): ${photos}")
//                textGeneralInfo.text = photos.toString()
                textDateUtc.text = photos?.getOrNull(count)?.date_utc
                textIcon.text  = photos?.getOrNull(count)?.links?.patch?.small.toString()
                textName.text  = photos?.getOrNull(count)?.name.toString()
                textRepeatedFlight.text = photos?.getOrNull(count)?.cores.toString()
                textMissionStatus.text = photos?.getOrNull(count)?.success.toString()
                Picasso.get()
                    .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
                    .into(imageIcon);
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
            Picasso.get()
                .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
                .into(imageIcon);
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
            Picasso.get()
                .load(photos?.getOrNull(count)?.links?.patch?.small.toString())
                .into(imageIcon);
//            imageIcon.setImageURI(Uri.parse(photos?.getOrNull(count)?.links?.patch?.small.toString()))
        }
    }
}