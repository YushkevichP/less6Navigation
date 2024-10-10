package com.example.less6navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.less6navigation.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "Error with _binding"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerVieww.layoutManager = LinearLayoutManager(view.context)

//            parentFragmentManager.setFragmentResultListener("test", viewLifecycleOwner,
//
//            )
            parentFragmentManager.setFragmentResultListener("test", viewLifecycleOwner){
                _, bundle ->
                toolbarMy.title = bundle.getInt("test_key").toString()

            }
            val listOfItems = List(30) {
                NoteItem(index = it, noteText = "item $it")
            }

            recyclerVieww.adapter = NotesAdapter(listOfItems) {
                findNavController().navigate(ListFragmentDirections.toCounter(it.index))
//                findNavController().navigate(R.id.to_counter, bundleOf("key" to it.index))
//                если бы не подключил safeArgs, то нужно было бы вот таким образом прописывать навигация
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}