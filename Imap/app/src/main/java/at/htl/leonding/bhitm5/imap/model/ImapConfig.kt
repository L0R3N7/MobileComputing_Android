package at.htl.leonding.bhitm5.imap.model


data class ImapConfig(var email: String, var name: String, var host: String, var hostName: String) {
    constructor() : this("", "", "", "")
}