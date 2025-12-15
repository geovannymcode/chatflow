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

data class UserRegisteredEvent(
    override val userId: UUID,
    val email: String,
    val name: String?,
    override val occurredAt: Instant = Instant.now()
) : UserEvent()

data class UserVerifiedEvent(
    override val userId: UUID,
    override val occurredAt: Instant = Instant.now()
) : UserEvent()

data class PasswordResetRequestedEvent(
    override val userId: UUID,
    val email: String,
    val resetToken: String,
    override val occurredAt: Instant = Instant.now()
) : UserEvent()

data class ResendVerificationRequestedEvent(
    override val userId: UUID,
    val email: String,
    val verificationToken: String,
    override val occurredAt: Instant = Instant.now()
) : UserEvent()