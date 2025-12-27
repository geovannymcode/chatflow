package com.chatflow.user.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Modelo de dominio para Token de Verificación de Email.
 */
data class EmailVerificationToken(
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

    fun markAsUsed(): EmailVerificationToken = copy(usedAt = Instant.now())

    companion object {
        private val DEFAULT_EXPIRATION_HOURS = 24L

        fun create(
            token: String,
            userId: UUID,
            expirationHours: Long = DEFAULT_EXPIRATION_HOURS
        ): EmailVerificationToken = EmailVerificationToken(
            id = UUID.randomUUID(),
            token = token,
            userId = userId,
            expiresAt = Instant.now().plusSeconds(expirationHours * 3600),
            createdAt = Instant.now(),
            usedAt = null
        )
    }
}