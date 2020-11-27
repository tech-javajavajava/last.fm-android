package technopark.andruxa.myapplication.search

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.R
import technopark.andruxa.myapplication.network.AlbumApi
import technopark.andruxa.myapplication.network.ArtistApi
import technopark.andruxa.myapplication.network.TrackApi

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private var container: ViewGroup? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        this.container = container
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.getSearchState().observe(viewLifecycleOwner, SearchResultsObserver(
            viewModel,
            container,
            activity,
            search_progress_bar,
            search_results_container
        ))

        search_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_input.clearFocus()
                viewModel.search(query, 3)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private class SearchResultsObserver(
        private val viewModel: SearchViewModel,
        private val container: ViewGroup?,
        private val activity: FragmentActivity?,
        private val progressBar: ProgressBar,
        private val searchResultsContainer: LinearLayout) : Observer<SearchViewModel.SearchProgress?> {

        override fun onChanged(searchState: SearchViewModel.SearchProgress?) {
            when {
                searchState === SearchViewModel.SearchProgress.ERROR -> {
                    progressBar.visibility = View.GONE
                }
                searchState === SearchViewModel.SearchProgress.IN_PROGRESS -> {
                    while (searchResultsContainer.size > 0) {
                        searchResultsContainer.removeViewAt(0)
                    }
                    progressBar.visibility = View.VISIBLE
                }
                searchState === SearchViewModel.SearchProgress.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    viewModel.tracks?.let {
                        val tracksShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        tracksShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.tracks_shortlist_caption)
                        val button: Button = tracksShortlist.findViewById<Button>(R.id.shortlist_button)
                        button.text = ApplicationModified.context?.getText(R.string.tracks_shortlist_button)
                        for (track: TrackApi.Track in it) {
                            Log.d("search render", track.name!!)
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            track.images?.get(0)?.url?.let {
                                setImage(v.findViewById(R.id.image), it)
                            }
                            val name: TextView = v.findViewById(R.id.name)
                            name.text = track.name
                            val artistName: TextView = v.findViewById(R.id.artist_name)
                            artistName.text = track.artist
                            // insert into main view
                            tracksShortlist.addView(v, tracksShortlist.size - 2)
                        }
                        tracksShortlist.visibility = View.VISIBLE
                        searchResultsContainer.addView(tracksShortlist)
                    }
                    viewModel.artists?.let {
                        val artistsShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        artistsShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.artists_shortlist_caption)
                        artistsShortlist.findViewById<Button>(R.id.shortlist_button).text =
                            ApplicationModified.context?.getText(R.string.artists_shortlist_button)
                        for (artist: ArtistApi.Artist in it) {
                            Log.d("search render", artist.name!!)
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.artist, container, false)
                            // fill in any details dynamically here
                            artist.images?.get(0)?.url?.let {
                                setImage(v.findViewById(R.id.image), it)
                            }
                            val name: TextView =
                                v.findViewById<View>(R.id.name) as TextView
                            name.text = artist.name
                            // insert into main view
                            artistsShortlist.addView(v, artistsShortlist.size - 2)
                        }
                        artistsShortlist.visibility = View.VISIBLE
                        searchResultsContainer.addView(artistsShortlist)
                    }
                    viewModel.albums?.let {
                        val albumsShortlist: LinearLayout = LayoutInflater.from(container?.context)
                            .inflate(R.layout.shortlist, container, false) as LinearLayout
                        albumsShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                            ApplicationModified.context?.getText(R.string.albums_shortlist_caption)
                        albumsShortlist.findViewById<Button>(R.id.shortlist_button).text =
                            ApplicationModified.context?.getText(R.string.albums_shortlist_button)
                        for (album: AlbumApi.Album in it) {
                            Log.d("search render", album.name!!)
                            val v: View = LayoutInflater.from(container?.context)
                                .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            album.images?.get(0)?.url?.let {
                                setImage(v.findViewById(R.id.image), it)
                            }
                            val name: TextView =
                                v.findViewById<View>(R.id.name) as TextView
                            name.text = album.name
                            val artistName: TextView =
                                v.findViewById<View>(R.id.artist_name) as TextView
                            artistName.text = album.artist
                            // insert into main view
                            albumsShortlist.addView(v, albumsShortlist.size - 2)
                        }
                        albumsShortlist.visibility = View.VISIBLE
                        searchResultsContainer.addView(albumsShortlist)
                    }
                }
                searchState === SearchViewModel.SearchProgress.FAILED -> {
                    progressBar.visibility = View.GONE
                }
                else -> {
                }
            }
        }

        fun setImage(image: ImageView, url: String) {
            GlobalScope.launch {
                val bmp: Bitmap = withContext(Dispatchers.IO) {
                    viewModel.getImage(url)
                }
                activity?.let { activity ->
                    activity.runOnUiThread {
                        image.setImageBitmap(bmp)
                    }
                }
            }
        }
    }
}