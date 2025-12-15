package com.chatflow.shared.events

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.Instant
import java.util.UUID

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = MessageSentEvent::class, name = "MESSAGE_SENT"),
    JsonSubTypes.Type(value = ChatCreatedEvent::class, name = "CHAT_CREATED"),
    JsonSubTypes.Type(value = ParticipantAddedEvent::class, name = "PARTICIPANT_ADDED")
)
sealed class ChatEvent {
    abstract val chatId: UUID
    abstract val occurredAt: Instant
}

data class MessageSentEvent(
    override val chatId: UUID,
    val messageId: UUID,
    val senderId: UUID,
    val senderName: String,
    val content: String,
    val recipientIds: List<UUID>,
    override val occurredAt: Instant = Instant.now()
) : ChatEvent()

data class ChatCreatedEvent(
    override val chatId: UUID,
    val createdBy: UUID,
    val participantIds: List<UUID>,
    override val occurredAt: Instant = Instant.now()
) : ChatEvent()

data class ParticipantAddedEvent(
    override val chatId: UUID,
    val userId: UUID,
    val addedBy: UUID,
    override val occurredAt: Instant = Instant.now()
) : ChatEvent()