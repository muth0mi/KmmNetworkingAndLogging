package app.kmmchat.logging

import co.touchlab.kermit.Kermit

object Logger {

    private val  kermit = Kermit()

    fun i(message: String, tag: String = "") {
        kermit.withTag(tag).i { message }
    }

    fun d(message: String, tag: String = "") {
        kermit.withTag(tag).d { message }
    }

    fun w(message: String, tag: String = "") {
        kermit.withTag(tag).w { message }
    }

    fun e(message: String, tag: String = "") {
        kermit.withTag(tag).e { message }
    }
}
