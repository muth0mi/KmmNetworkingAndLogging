package app.kmmchat.chat.presentation

import app.kmmchat.chat.domain.ChatMessage

sealed class ChatScreenEvent {
    data class NavigateUp(val navigate: Boolean = true) : ChatScreenEvent()
    data class DisplayMessage(val message: ChatMessage) : ChatScreenEvent()
    data class TypeMessageText(val text: String) : ChatScreenEvent()
    object OpenConnection : ChatScreenEvent()
    object CloseConnection : ChatScreenEvent()
    object SendMessage : ChatScreenEvent()
    object ObserveMessages : ChatScreenEvent()
}