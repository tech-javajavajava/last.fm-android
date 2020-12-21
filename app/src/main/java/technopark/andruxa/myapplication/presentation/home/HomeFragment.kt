package technopark.andruxa.myapplication.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.R
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.presentation.track.TrackFragment

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getChartsState().observe(viewLifecycleOwner, ChartsObserver(
            viewModel,
            container,
            parentFragmentManager,
            viewLifecycleOwner,
            view.findViewById(R.id.charts_container)
        ))
        viewModel.getCharts(true)
    }

    private class ChartsObserver(
        private val viewModel: HomeViewModel,
        private val container: ViewGroup?,
        private val fragmentManager: FragmentManager,
        private val viewLifecycleOwner: LifecycleOwner,
        private val chartsContainer: LinearLayout
    ): Observer<HomeViewModel.ChartsProgress?> {
        override fun onChanged(chartsState: HomeViewModel.ChartsProgress?) {
            if (chartsState == null) return
            when (chartsState.state) {
                HomeViewModel.ChartsProgress.State.IN_PROGRESS -> {
                }
                HomeViewModel.ChartsProgress.State.SUCCESS -> {
                    chartsContainer.removeAllViews()
                    chartsState.tracks?.let {
                        val tracksShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        tracksShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.tracks_shortlist_caption)
                        val button: Button = tracksShortlist.findViewById(R.id.shortlist_button)
                        button.text = ApplicationModified.context?.getText(R.string.tracks_shortlist_button)
                        for (track: Track in it) {
                            Log.d("charts render track", track.name.toString())
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            track.images.small?.let {
                                Picasso.get().load(it.url).into(v.findViewById(R.id.image) as ImageView)
                            }
                            val name: TextView = v.findViewById(R.id.name)
                            name.text = track.name
                            val artistName: TextView = v.findViewById(R.id.artist_name)
                            artistName.text = track.artistName
                            // insert into main view
                            track.name?.let { trackName ->
                                track.artistName?.let { artistName ->
                                    v.setOnClickListener{
                                        fragmentManager
                                                .beginTransaction()
                                                .replace(R.id.nav_host_fragment, TrackFragment(trackName, artistName))
                                                .addToBackStack(null)
                                                .commit()
                                    }
                                }
                            }
                            tracksShortlist.addView(v, tracksShortlist.size - 2)
                        }
                        tracksShortlist.visibility = View.VISIBLE
                        chartsContainer.addView(tracksShortlist)
                    }
                    chartsState.artists?.let {
                        Log.d("charts render artist", it.size.toString())
                        val artistsShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        artistsShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.artists_shortlist_caption)
                        artistsShortlist.findViewById<Button>(R.id.shortlist_button).text =
                            ApplicationModified.context?.getText(R.string.artists_shortlist_button)
                        for (artist: Artist in it) {
                            Log.d("charts render artist", artist.name.toString())
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.artist, container, false)
                            // fill in any details dynamically here
                            artist.images.small?.let {
                                Picasso.get().load(it.url).into(v.findViewById(R.id.image) as ImageView)
                            }
                            val name: TextView = v.findViewById(R.id.name) as TextView
                            name.text = artist.name
                            // insert into main view
                            artistsShortlist.addView(v, artistsShortlist.size - 2)
                        }
                        artistsShortlist.visibility = View.VISIBLE
                        chartsContainer.addView(artistsShortlist)
                    }
                    chartsState.tags?.let {
                        val tagsShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        tagsShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.tags_shortlist_caption)
                        tagsShortlist.findViewById<Button>(R.id.shortlist_button).text =
                            ApplicationModified.context?.getText(R.string.tags_shortlist_button)
                        Log.d("charts render tag", it.size.toString())
                        for (i: Int in it.indices step 3) {
                            val row: LinearLayout = LayoutInflater.from(container?.context)
                                .inflate(R.layout.tags_row, container, false) as LinearLayout
                            for (j in i..i+2) {
                                val tag = it[j]
                                Log.d("charts render tag", tag.name!!)
                                val v: View = LayoutInflater.from(container?.context)
                                    .inflate(R.layout.shortlist_tag, container, false)
                                // fill in any details dynamically here
                                val tagTopArtist = getTagImageUrl(tag.name!!)
                                tagTopArtist.state.observe(viewLifecycleOwner, {
                                        if (tagTopArtist.isOk()) {
                                            tagTopArtist.data?.let {
                                                it.get(0).images.small?.let {
                                                    Picasso.get().load(it.url).into(v.findViewById(R.id.image) as ImageView)
                                                }
                                            }
                                        }
                                    })
                                val name: TextView = v.findViewById(R.id.name) as TextView
                                name.text = tag.name
                                // insert into main view
                                row.addView(v)
                            }
                            tagsShortlist.addView(row, tagsShortlist.size - 2)
                        }
                        tagsShortlist.visibility = View.VISIBLE
                        chartsContainer.addView(tagsShortlist)
                    }
                }
                HomeViewModel.ChartsProgress.State.FAILED -> {
                }
            }
        }

        fun getTagImageUrl(name: String): SDataI<List<Artist>> {
            return viewModel.getTagImageUrl(name)
        }
    }
}