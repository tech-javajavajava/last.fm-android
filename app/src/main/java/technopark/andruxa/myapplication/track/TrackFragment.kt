package technopark.andruxa.myapplication.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import technopark.andruxa.myapplication.R

class TrackFragment : Fragment() {
//    private lateinit var viewModel: TrackViewModel
//    private var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        this.container = container
//        viewModel = ViewModelProvider(this).get(TrackViewModel::class.java)
        return inflater.inflate(R.layout.fragment_track, container, false)
    }
}