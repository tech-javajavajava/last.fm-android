package technopark.andruxa.myapplication.presentation.track

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.R
import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.models.track.Track

class TrackFragment() : Fragment() {
    constructor(trackName: String, trackArtist: String) : this() {
        this.trackName = trackName
        this.trackArtist = trackArtist
    }

    companion object {
        private const val NAME_KEY = "trackName"
        private const val ARTIST_KEY = "trackArtist"
    }

    private lateinit var trackName: String
    private lateinit var trackArtist: String
    private lateinit var viewModel: TrackViewModel
    private var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        viewModel = ViewModelProvider(this).get(TrackViewModel::class.java)
        return inflater.inflate(R.layout.fragment_track, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("track fragment", "restoring state...")
        Log.d("track fragment", savedInstanceState?.getString(NAME_KEY).toString())
        Log.d("track fragment", savedInstanceState?.getString(ARTIST_KEY).toString())
        savedInstanceState?.let {
            trackName = it.getString(NAME_KEY, "")
            trackArtist = it.getString(ARTIST_KEY, "")
        }
        viewModel = ViewModelProvider(this).get(TrackViewModel::class.java)
        viewModel.getTrackState().observe(viewLifecycleOwner, TrackObserver(
            viewModel,
            container,
            activity,
            this,
            parentFragmentManager,
            view
        )
        )
        viewModel.getTrack(trackName, trackArtist)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, trackName)
        outState.putString(ARTIST_KEY, trackArtist)
        Log.d("track fragment", "saving state...")
    }

    fun onWikiExpanderClickListener(lessTextWiki: String?, moreTextWiki: String?) {
        val wiki = view?.findViewById<TextView>(R.id.wiki)
        val expander = view?.findViewById<TextView>(R.id.wiki_expander)
        val expanderExpandText = ApplicationModified.context?.getText(R.string.track_wiki_expander_expand_text)
        val expanderReduceText = ApplicationModified.context?.getText(R.string.track_wiki_expander_reduce_text)
        expander?.let {
            wiki?.let {
                when (expander.text) {
                    expanderExpandText -> {
                        expander.text = expanderReduceText
                        wiki.text = moreTextWiki
                    }
                    expanderReduceText -> {
                        expander.text = expanderExpandText
                        wiki.text = lessTextWiki
                    }
                }
            }
        }
    }

    private class TrackObserver(
        private val viewModel: TrackViewModel,
        private val container: ViewGroup?,
        private val activity: FragmentActivity?,
        private val clickListener: TrackFragment,
        private val fragmentManager: FragmentManager,
        private val view: View
    ): Observer<TrackViewModel.TrackProgress?> {
        override fun onChanged(trackState: TrackViewModel.TrackProgress?) {
            if (trackState == null) return
            val trackView = view.findViewById<LinearLayout>(R.id.track)
            when {
                trackState.state === TrackViewModel.TrackProgress.State.IN_PROGRESS -> {
                    trackView.visibility = View.GONE
                    view.findViewById<ProgressBar>(R.id.track_progress_bar).visibility = View.VISIBLE
                }
                trackState.state === TrackViewModel.TrackProgress.State.SUCCESS -> {
                    view.findViewById<ProgressBar>(R.id.track_progress_bar).visibility = View.GONE
                    trackState.track?.let {
                        Log.d("track render", it.name!!)
                        trackView.findViewById<TextView>(R.id.track_name).text = it.name
                        trackView.findViewById<TextView>(R.id.track_artist_name).text = it.artistName
//                        trackView.findViewById<TextView>(R.id.track_duration).text = Utils.millisecondsToString(it.duration!!)
                        trackView.findViewById<TextView>(R.id.track_listeners).text = Utils.amountToString(it.listeners!!)
//                        trackView.findViewById<TextView>(R.id.track_playcount).text = Utils.amountToString(it.playcount!!)
                        val tagsContainer = trackView.findViewById<FlexboxLayout>(R.id.tags_container)
//                        it.topTags?.let {topTags ->
//                            for (tag: Tag in topTags) {
//                                val tagView = LayoutInflater.from(container?.context)
//                                    .inflate(R.layout.tag, container, false) as TextView
//                                tagView.text = tag.name
//                                tagsContainer.addView(tagView)
//                            }
//                        }
//                        trackView.findViewById<TextView>(R.id.album_name).text = it.album!!.name
//                        trackView.findViewById<TextView>(R.id.album_artist_name).text = it.album!!.artist
//                        it.images?.get(0)?.url?.let {
//                            setImage(trackView.findViewById(R.id.album_image), it)
//                        }
//                        trackView.findViewById<TextView>(R.id.wiki).text = it.wiki!!.summary
//                        trackView.findViewById<TextView>(R.id.wiki_expander).setOnClickListener{_ ->
//                            clickListener.onWikiExpanderClickListener(it.wiki!!.summary, it.wiki!!.content)
//                        }
                    }
                    trackState.similarTracks?.let {
                        Log.d("track render similar", it.size.toString())
                        for (track: Track in it) {
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            v.findViewById<TextView>(R.id.name).text = track.name
                            v.findViewById<TextView>(R.id.artist_name).text = track.artistName
//                            track.images?.get(0)?.url?.let {
//                                setImage(v.findViewById(R.id.image), it)
//                            }
                            // insert into main view
                            v.setOnClickListener{
                                fragmentManager
                                    .beginTransaction()
                                    .replace(R.id.nav_host_fragment, TrackFragment(track.name!!, track.artist!!))
                                    .addToBackStack(null)
                                    .commit()
                            }
                            trackView.addView(v)
                        }
                    }
                    trackView.visibility = View.VISIBLE
                }
                trackState.state === TrackViewModel.TrackProgress.State.FAILED -> {
                    view.findViewById<ProgressBar>(R.id.track_progress_bar).visibility = View.GONE
                }
            }
        }
    }
}