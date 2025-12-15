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
    JsonSubTypes.Type(value = UserRegisteredEvent::class, name = "USER_REGISTERED"),
    JsonSubTypes.Type(value = UserVerifiedEvent::class, name = "USER_VERIFIED"),
    JsonSubTypes.Type(value = PasswordResetRequestedEvent::class, name = "PASSWORD_RESET_REQUESTED")
)
sealed class UserEvent {
    abstract val userId: UUID
    abstract val occurredAt: Instant
}