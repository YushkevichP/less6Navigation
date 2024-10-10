package com.example.less6navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.less6navigation.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private var _binding: FragmentCounterBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "ERROR"
        }

    //таким вот образом можно вытащить аргументы все
    private val args by navArgs<CounterFragmentArgs>()
    private val textForMessage = "Bye-Bye"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCounterBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
//            var counter = requireArguments().getInt("key")
//            если бы не подключил safeArgs, то нужно было бы вот таким образом прописывать как выттащить аргументы

            var counter = args.keyCounter



//            для переходов назад
           toolbarMy.setOnClickListener {
               findNavController().navigateUp()
               parentFragmentManager.setFragmentResult("test", bundleOf("test_key" to counter))
           }

            textView.text = counter.toString()

            textView.setOnClickListener {
                textView.text = textForMessage
            }

            plusButton.setOnClickListener {
                ++counter
                textView.text = counter.toString()
            }

            minusButton.setOnClickListener {
                --counter
                textView.text = counter.toString()
            }

            resetButton.setOnClickListener {
                counter = 0
                textView.text = counter.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}