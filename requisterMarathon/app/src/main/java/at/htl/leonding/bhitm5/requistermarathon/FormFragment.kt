package at.htl.leonding.bhitm5.requistermarathon

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import at.htl.leonding.bhitm5.requistermarathon.databinding.FragmentFormBinding
import at.htl.leonding.bhitm5.requistermarathon.model.Runner

class FormFragment : Fragment() {
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentFormBinding.inflate(inflater)



        binding.button2.setOnClickListener {
            sharedViewModel.addRunner(Runner(binding.tiFirstname.text.toString(), binding.tiLastname.text.toString(), binding.niAge.text.toString().toInt()))
            it.findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}