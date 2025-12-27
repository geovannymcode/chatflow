package com.chatflow.user.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Modelo de dominio para Token de Reset de Password.
 */
data class PasswordResetToken(
    val id: UUID,
    val token: String,
    val userId: UUID,
    val expiresAt: Instant,
    val createdAt: Instant,
    val usedAt: Instant?
) {
    fun isValid(): Boolean = usedAt == null && expiresAt.isAfter(Instant.now())

    fun isUsed(): Boolean = usedAt != null

    fun isExpired(): Boolean = expiresAt.isBefore(Instant.now())

    fun markAsUsed(): PasswordResetToken = copy(usedAt = Instant.now())

    companion object {
        private val DEFAULT_EXPIRATION_MINUTES = 60L

        fun create(
            token: String,
            userId: UUID,
            expirationMinutes: Long = DEFAULT_EXPIRATION_MINUTES
        ): PasswordResetToken = PasswordResetToken(
            id = UUID.randomUUID(),
            token = token,
            userId = userId,
            expiresAt = Instant.now().plusSeconds(expirationMinutes * 60),
            createdAt = Instant.now(),
            usedAt = null
        )
    }
}