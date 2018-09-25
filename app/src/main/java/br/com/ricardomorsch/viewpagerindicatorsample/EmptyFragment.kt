package br.com.ricardomorsch.viewpagerindicatorsample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_empty.*

class EmptyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        label.text = arguments?.getString(LABEL_ARGUMENT) ?: ""
    }

    companion object {

        private const val LABEL_ARGUMENT = "LABEL_ARGUMENT"

        fun newInstance(label: String): EmptyFragment {

            val fragment = EmptyFragment()

            val bundle = Bundle()
            bundle.putString(LABEL_ARGUMENT, label)

            fragment.arguments = bundle

            return fragment
        }
    }
}