package com.chatflow.user.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Modelo de dominio para Refresh Token.
 * Representa un token de refresco almacenado en la base de datos.
 */
data class RefreshToken(
    val id: UUID,
    val token: String,
    val userId: UUID,
    val expiresAt: Instant,
    val createdAt: Instant,
    val revokedAt: Instant?
) {
    /**
     * Verifica si el token es válido (no expirado y no revocado).
     */
    fun isValid(): Boolean = revokedAt == null && expiresAt.isAfter(Instant.now())

    /**
     * Verifica si el token está expirado.
     */
    fun isExpired(): Boolean = expiresAt.isBefore(Instant.now())

    /**
     * Verifica si el token ha sido revocado.
     */
    fun isRevoked(): Boolean = revokedAt != null

    /**
     * Crea una copia del token marcado como revocado.
     */
    fun revoke(): RefreshToken = copy(revokedAt = Instant.now())

    companion object {
        fun create(
            token: String,
            userId: UUID,
            expiresAt: Instant
        ): RefreshToken = RefreshToken(
            id = UUID.randomUUID(),
            token = token,
            userId = userId,
            expiresAt = expiresAt,
            createdAt = Instant.now(),
            revokedAt = null
        )
    }
}