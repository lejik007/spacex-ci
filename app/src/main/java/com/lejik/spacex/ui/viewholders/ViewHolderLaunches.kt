package com.lejik.spacex.ui.viewholders

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lejik.spacex.R
import com.lejik.spacex.databinding.LaunchGeneralResItemsBinding
import com.lejik.spacex.model.Docs

class ViewHolderLaunches(
    private val binding: LaunchGeneralResItemsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(docs: Docs) {
        binding.apply {
            textDateUtc.text = docs.date_utc.toString()
            textIcon.text = docs.links?.patch?.small.toString()
            textName.text = docs.name.toString()
            textRepeatedFlight.text = docs.cores.toString()
            textMissionStatus.text =  docs.success.toString()
            imageIcon.load(
                docs.links?.patch?.small.toString().toUri().buildUpon().scheme("https").build()
            ) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }
}