package at.htl.leonding.bhitm5.imap

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import at.htl.leonding.bhitm5.imap.databinding.FragmentSelectionOverviewBinding
import at.htl.leonding.bhitm5.imap.model.ImapConfig

class SelectionOverviewFragment : Fragment() {

    var imapConfig: ImapConfig = ImapConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load Data
        AndroidBus.behaverSubject.subscribe{
            if (it is ImapConfig){
                imapConfig = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var binding: FragmentSelectionOverviewBinding = FragmentSelectionOverviewBinding.inflate(inflater)

        setupMenu()

        binding.tvEmail.setText(if (imapConfig.email.isEmpty()) "no Value" else imapConfig.email)
        binding.tvImapHost.setText(if (imapConfig.host.isEmpty()) "no Value" else imapConfig.host)
        binding.tvImapHostName.setText(if (imapConfig.hostName.isEmpty()) "no Value" else imapConfig.hostName)
        binding.tvName.setText(if (imapConfig.name.isEmpty()) "no Value" else imapConfig.name)

        binding.button.setOnClickListener { it.findNavController().navigate(R.id.action_selectionOverviewFragment_to_selectionFragment) }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupMenu() {
        (requireActivity() as MainActivity).addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.mainmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}