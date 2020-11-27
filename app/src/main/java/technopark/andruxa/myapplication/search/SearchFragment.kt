package technopark.andruxa.myapplication.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        val searchInput: SearchView = view.findViewById(R.id.search_input)
        val tracksContainer: LinearLayout = view.findViewById(R.id.search_tracks_container)
        val artistsContainer: LinearLayout = view.findViewById(R.id.search_artists_container)
        val albumsContainer: LinearLayout = view.findViewById(R.id.search_albums_container)

        viewModel.getSearchState().observe(viewLifecycleOwner, SearchResultsObserver(viewModel, container, tracksContainer, artistsContainer, albumsContainer))

        searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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
            private val tracksContainer: LinearLayout,
            private val artistsContainer: LinearLayout,
            private val albumsContainer: LinearLayout) : Observer<SearchViewModel.SearchProgress?> {

        override fun onChanged(searchState: SearchViewModel.SearchProgress?) {
            when {
                searchState === SearchViewModel.SearchProgress.ERROR -> {
                }
                searchState === SearchViewModel.SearchProgress.IN_PROGRESS -> {
                }
                searchState === SearchViewModel.SearchProgress.TRACKS_SUCCESS -> {
                    while (tracksContainer.size > 2) {
                        tracksContainer.removeViewAt(1)
                    }
                    for (track: TrackApi.Track in viewModel.tracks!!) {
                        Log.d("search render", track.name!!)
                        val v: View = LayoutInflater.from(container?.context).inflate(R.layout.search_container_element, container, false)
                        // fill in any details dynamically here
//                        val url = URL(track.images?.get(0)?.url)
//                        val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                        val image: ImageView = v.findViewById<View>(R.id.search_container_element_image) as ImageView
//                        image.setImageBitmap(bmp)
                        val caption: TextView = v.findViewById<View>(R.id.search_container_element_caption) as TextView
                        caption.text = track.name
                        val text: TextView = v.findViewById<View>(R.id.search_container_element_text) as TextView
                        text.text = track.artist
                        // insert into main view
                        tracksContainer.addView(v, tracksContainer.size - 1)
                    }
                }
                searchState === SearchViewModel.SearchProgress.ARTISTS_SUCCESS -> {
                    while (artistsContainer.size > 2) {
                        artistsContainer.removeViewAt(1)
                    }
                    for (artist: ArtistApi.Artist in viewModel.artists!!) {
                        Log.d("search render", artist.name!!)
                        val v: View = LayoutInflater.from(container?.context).inflate(R.layout.search_container_element, container, false)
                        // fill in any details dynamically here
//                        val url = URL(track.images?.get(0)?.url)
//                        val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                        val image: ImageView = v.findViewById<View>(R.id.search_container_element_image) as ImageView
//                        image.setImageBitmap(bmp)
                        val caption: TextView = v.findViewById<View>(R.id.search_container_element_caption) as TextView
                        caption.text = artist.name
                        // insert into main view
                        artistsContainer.addView(v, artistsContainer.size - 1)
                    }
                }
                searchState === SearchViewModel.SearchProgress.ALBUMS_SUCCESS -> {
                    while (albumsContainer.size > 2) {
                        albumsContainer.removeViewAt(1)
                    }
                    for (album: AlbumApi.Album in viewModel.albums!!) {
                        Log.d("search render", album.name!!)
                        val v: View = LayoutInflater.from(container?.context).inflate(R.layout.search_container_element, container, false)
                        // fill in any details dynamically here
//                        val url = URL(track.images?.get(0)?.url)
//                        val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                        val image: ImageView = v.findViewById<View>(R.id.search_container_element_image) as ImageView
//                        image.setImageBitmap(bmp)
                        val caption: TextView = v.findViewById<View>(R.id.search_container_element_caption) as TextView
                        caption.text = album.name
                        val text: TextView = v.findViewById<View>(R.id.search_container_element_text) as TextView
                        text.text = album.artist
                        // insert into main view
                        albumsContainer.addView(v, albumsContainer.size - 1)
                    }
                }
                else -> {
                }
            }
        }
    }
}