package at.htl.leonding.bhitm5.requistermarathon

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import at.htl.leonding.bhitm5.requistermarathon.databinding.FragmentListBinding
import at.htl.leonding.bhitm5.requistermarathon.model.Runner

class ListFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val runnerList: MutableList<Runner> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentListBinding = FragmentListBinding.inflate(inflater)

        this.sharedViewModel.runner.observe(viewLifecycleOwner) {
            var text = ""
            for ( runner: Runner in it ){
                text = text + runner.toString() + "\n"
                Log.d("mons", text)
            }
            binding.textView4.setText(text)
        }

        binding.button.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }
        setupMenu()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupMenu(){
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.mainmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}