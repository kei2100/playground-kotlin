class CUser private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = CUser(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = CUser(accountId.toString())
    }
}
