package technopark.andruxa.myapplication.presentation.track

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout
import com.squareup.picasso.Picasso
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.R
import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.models.tag.Tag

class AlbumFragment() : Fragment() {
    constructor(albumName: String, albumArtist: String) : this() {
        this.albumName = albumName
        this.albumArtist = albumArtist
    }

    companion object {
        private const val NAME_KEY = "albumName"
        private const val ARTIST_KEY = "albumArtist"
    }

    private lateinit var albumName: String
    private lateinit var albumArtist: String
    private lateinit var viewModel: AlbumViewModel
    private var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("album fragment", "restoring state...")
        Log.d("album fragment", savedInstanceState?.getString(NAME_KEY).toString())
        Log.d("album fragment", savedInstanceState?.getString(ARTIST_KEY).toString())
        savedInstanceState?.let {
            albumName = it.getString(NAME_KEY, "")
            albumArtist = it.getString(ARTIST_KEY, "")
        }
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        viewModel.getAlbumState().observe(viewLifecycleOwner, AlbumObserver(
            viewModel,
            container,
            activity,
            this,
            parentFragmentManager,
            view
        ))
        viewModel.getAlbum(albumName, albumArtist)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, albumName)
        outState.putString(ARTIST_KEY, albumArtist)
        Log.d("album fragment", "saving state...")
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

    private class AlbumObserver(
        private val viewModel: AlbumViewModel,
        private val container: ViewGroup?,
        private val activity: FragmentActivity?,
        private val clickListener: AlbumFragment,
        private val fragmentManager: FragmentManager,
        private val view: View
    ): Observer<AlbumViewModel.AlbumProgress?> {
        override fun onChanged(albumState: AlbumViewModel.AlbumProgress?) {
            if (albumState == null) return
            val albumView = view.findViewById<LinearLayout>(R.id.album)
            when (albumState.state) {
                AlbumViewModel.AlbumProgress.State.IN_PROGRESS -> {
                    albumView.visibility = View.GONE
                    view.findViewById<ProgressBar>(R.id.album_progress_bar).visibility = View.VISIBLE
                }
                AlbumViewModel.AlbumProgress.State.SUCCESS -> {
                    view.findViewById<ProgressBar>(R.id.album_progress_bar).visibility = View.GONE
                    albumState.album?.let {
                        Log.d("album render", it.name!!)
                        albumView.findViewById<TextView>(R.id.album_name).text = it.name
                        albumView.findViewById<TextView>(R.id.album_artist_name).text = it.artistName
                        it.images.small?.let {
                            Picasso.get().load(it.url)
                                .into(albumView.findViewById(R.id.album_image) as ImageView)
                        }
                        albumView.findViewById<TextView>(R.id.album_listeners).text = Utils.amountToString(it.listeners!!)
                        albumView.findViewById<TextView>(R.id.album_playcount).text = Utils.amountToString(it.playcount!!)
                        val tagsContainer = albumView.findViewById<FlexboxLayout>(R.id.tags_container)
                        it.tags?.let {tags ->
                            for (tag: Tag in tags) {
                                val tagView = LayoutInflater.from(container?.context)
                                    .inflate(R.layout.tag, container, false) as TextView
                                tagView.text = tag.name
                                tagsContainer.addView(tagView)
                            }
                        }
                        albumView.findViewById<TextView>(R.id.wiki).text = it.summary
                        albumView.findViewById<TextView>(R.id.wiki_expander).setOnClickListener{_ ->
                            clickListener.onWikiExpanderClickListener(it.summary, it.content)
                        }
                    }
                    albumView.visibility = View.VISIBLE
                }
                AlbumViewModel.AlbumProgress.State.FAILED -> {
                    view.findViewById<ProgressBar>(R.id.album_progress_bar).visibility = View.GONE
                }
            }
        }
    }
}