package at.htl.leonding.bhitm5.requistermarathon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.htl.leonding.bhitm5.requistermarathon.model.Runner

class SharedViewModel : ViewModel() {
    private var _runner = MutableLiveData(ArrayList<Runner>())
    val runner: LiveData<ArrayList<Runner>> = _runner

    fun addRunner(newRunner: Runner){
        _runner.value!!.add(newRunner)
    }
}