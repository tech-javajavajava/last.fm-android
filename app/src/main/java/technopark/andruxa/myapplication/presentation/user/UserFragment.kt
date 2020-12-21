package technopark.andruxa.myapplication.presentation.user

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.R
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.presentation.track.TrackFragment

class UserFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        if (!viewModel.checkLogin()) {
            parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, AuthFragment())
                    .commit()
            return
        }

        Log.d("user", "logged in")
        viewModel.getProfileProgress().observe(viewLifecycleOwner, ProfileObserver(
            container,
            parentFragmentManager,
            view.findViewById(R.id.username),
            view.findViewById(R.id.profile_container)
        ))
        viewModel.getProfile()
    }

    private class ProfileObserver(
            private val container: ViewGroup?,
            private val fragmentManager: FragmentManager,
            private val usernameContainer: TextView,
            private val profileContainer: LinearLayout
    ): Observer<UserViewModel.ProfileProgress?> {
        override fun onChanged(profileState: UserViewModel.ProfileProgress?) {
            if (profileState == null) return
            when (profileState.state) {
                UserViewModel.ProfileProgress.State.IN_PROGRESS -> {
                }
                UserViewModel.ProfileProgress.State.SUCCESS -> {
                    profileContainer.removeAllViews()
                    profileState.username?.let {
                        usernameContainer.text = it
                    }
                    profileState.loved?.let {
                        val tracksShortlist: LinearLayout = LayoutInflater.from(container?.context)
                                .inflate(R.layout.shortlist, container, false) as LinearLayout
                        tracksShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                                ApplicationModified.context?.getText(R.string.loved_tracks_shortlist_caption)
                        val button: Button = tracksShortlist.findViewById(R.id.shortlist_button)
                        button.text = ApplicationModified.context?.getText(R.string.tracks_shortlist_button)
                        for (track: Track in it) {
                            Log.d("loved render", track.name.toString())
                            val v: View = LayoutInflater.from(container?.context)
                                    .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            track.images.small?.let {
                                if (it.url != "") {
                                    Picasso.get().load(it.url)
                                        .into(v.findViewById(R.id.image) as ImageView)
                                }
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
                        profileContainer.addView(tracksShortlist)
                    }
                    profileState.recent?.let {
                        val tracksShortlist: LinearLayout = LayoutInflater.from(container?.context)
                                .inflate(R.layout.shortlist, container, false) as LinearLayout
                        tracksShortlist.findViewById<TextView>(R.id.shortlist_caption).text =
                                ApplicationModified.context?.getText(R.string.recent_tracks_shortlist_caption)
                        val button: Button = tracksShortlist.findViewById(R.id.shortlist_button)
                        button.text = ApplicationModified.context?.getText(R.string.tracks_shortlist_button)
                        for (track: Track in it) {
                            Log.d("recent render", track.name.toString())
                            val v: View = LayoutInflater.from(container?.context)
                                    .inflate(R.layout.track_or_album, container, false)
                            // fill in any details dynamically here
                            track.images.small?.let {
                                if (it.url != "") {
                                    Picasso.get().load(it.url)
                                        .into(v.findViewById(R.id.image) as ImageView)
                                }
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
                        profileContainer.addView(tracksShortlist)
                    }
                }
                UserViewModel.ProfileProgress.State.FAILED -> {
                }
            }
        }
    }
}