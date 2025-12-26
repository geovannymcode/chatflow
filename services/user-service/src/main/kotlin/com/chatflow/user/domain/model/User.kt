package com.chatflow.user.domain.model

import java.time.Instant
import java.util.UUID

/**
 * Modelo de dominio para Usuario.
 * Representa un usuario del sistema con todos sus atributos.
 */
data class User(
    val id: UUID,
    val email: String,
    val passwordHash: String,
    val name: String?,
    val profilePictureUrl: String?,
    val isVerified: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    /**
     * Verifica si el usuario puede realizar login.
     * Un usuario debe estar verificado para hacer login.
     */
    fun canLogin(): Boolean = isVerified

    /**
     * Crea una copia del usuario marcado como verificado.
     */
    fun verify(): User = copy(
        isVerified = true,
        updatedAt = Instant.now()
    )

    /**
     * Crea una copia del usuario con nuevo password hash.
     */
    fun updatePassword(newPasswordHash: String): User = copy(
        passwordHash = newPasswordHash,
        updatedAt = Instant.now()
    )

    /**
     * Crea una copia del usuario con perfil actualizado.
     */
    fun updateProfile(newName: String?, newProfilePictureUrl: String?): User = copy(
        name = newName ?: name,
        profilePictureUrl = newProfilePictureUrl ?: profilePictureUrl,
        updatedAt = Instant.now()
    )

    companion object {
        fun create(
            email: String,
            passwordHash: String,
            name: String? = null
        ): User = User(
            id = UUID.randomUUID(),
            email = email.lowercase().trim(),
            passwordHash = passwordHash,
            name = name?.trim(),
            profilePictureUrl = null,
            isVerified = false,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )
    }
}