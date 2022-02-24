package com.example.mynotes.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.example.mynotes.MainActivity
import com.example.mynotes.databinding.ListDetailFragmentBinding
import com.example.mynotes.models.TaskList
import com.example.mynotes.ui.main.MainViewModel
import com.example.mynotes.ui.main.MainViewModelFactory

class ListDetailFragment : Fragment() {
    lateinit var binding: ListDetailFragmentBinding

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
        )
            .get(MainViewModel::class.java)
        val list: TaskList? = arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)
        list?.let {
            viewModel.list = list
            requireActivity().title = list.name
        }
        val adapter = ListItemsRecyclerViewAdapter(viewModel.list)

        viewModel.onTaskAdded = {
            adapter.notifyDataSetChanged()
        }

        try {
            (activity as MainActivity?)?.LoadEditText()
        }
        catch (e: ClassCastException) { null }
        finally {

        }



    }


}