package com.thing.baby.model

import java.util.*

class Message(
    val senderUid: String,
    val isMedia: Boolean,
    val message: String,
    val sentTimestamp: Date,
    val receipt: Int = SENT
) {
    companion object {
        const val SENT = 0
        const val RECEIVED = 1
        const val READ = 2
    }
}