// このコードはコンパイルエラー
//fun <T> isA(value: Any) = value is T

// inline にし、型引数に reified を付与するとコンパイル可能
inline fun <reified T> isA(value: T) = value is T
