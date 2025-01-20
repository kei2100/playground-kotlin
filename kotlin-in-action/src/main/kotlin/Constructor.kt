// プライマリコンストラクタを持つ
class User(val nickname: String)

// プライマリコンストラクタを持つ
class User2 constructor(_nickname: String) {
    val nickname: String

    // 初期化ブロックを持つ
    init {
        nickname = _nickname
    }
}

class User3 {
    lateinit var nickname: String

    // セカンダリコンストラクタ
    constructor(_nickname: String) {
        nickname = _nickname
    }
    // セカンダリコンストラクタから上記セカンダリコンストラクタを呼び出す
    constructor(facebookAccountId: Int): this(facebookAccountId.toString())
}
