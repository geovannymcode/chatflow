package com.chatflow.shared.domain

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val name: String?,
    val profilePictureUrl: String?,
    val isVerified: Boolean,
    val createdAt: Instant
)
