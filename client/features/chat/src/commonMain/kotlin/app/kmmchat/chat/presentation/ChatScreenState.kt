package app.kmmchat.chat.presentation

import app.kmmchat.chat.domain.ChatMessage

data class ChatScreenState(
    val navigatingUp: Boolean = false,
    val sendingMessage: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val typing: Boolean = false,
    val messageText: String = "",
)