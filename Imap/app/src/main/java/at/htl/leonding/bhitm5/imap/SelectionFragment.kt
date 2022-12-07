package at.htl.leonding.bhitm5.imap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import at.htl.leonding.bhitm5.imap.databinding.FragmentSelectionBinding
import at.htl.leonding.bhitm5.imap.model.ImapConfig

class SelectionFragment : Fragment() {

    var imapConfig = ImapConfig();
    lateinit var binding: FragmentSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidBus.behaverSubject.subscribe{
            if(it is ImapConfig){
                imapConfig = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectionBinding.inflate(inflater)

        binding.tiEmail.setText(imapConfig.email)
        binding.tiHost.setText(imapConfig.host)
        binding.tiHostname.setText(imapConfig.hostName)
        binding.tiName.setText(imapConfig.name)

        binding.tiEmail.addTextChangedListener{disabledButtonWhenFormIsntValid()}
        binding.tiHost.addTextChangedListener{disabledButtonWhenFormIsntValid()}
        binding.tiHostname.addTextChangedListener{disabledButtonWhenFormIsntValid()}
        binding.tiName.addTextChangedListener{disabledButtonWhenFormIsntValid()}

        binding.button2.isEnabled = false
        binding.button2.setOnClickListener {
            AndroidBus.behaverSubject.onNext(ImapConfig(binding.tiEmail.text.toString(), binding.tiName.text.toString(), binding.tiHost.text.toString(), binding.tiHostname.text.toString()))
            it.findNavController().navigate(R.id.action_selectionFragment_to_selectionOverviewFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun disabledButtonWhenFormIsntValid(){
        var isValid = !(binding.tiHost.text?.isEmpty() ?: true || binding.tiEmail.text?.isEmpty() ?: true || binding.tiName.text?.isEmpty() ?: true || binding.tiHostname.text?.isEmpty() ?: true)
        binding.button2.isEnabled = isValid
    }
}