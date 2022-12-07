package at.htl.leonding.bhitm5.imap

import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidBus {
    companion object{
        val behaverSubject = BehaviorSubject.create<Any>()
    }
}