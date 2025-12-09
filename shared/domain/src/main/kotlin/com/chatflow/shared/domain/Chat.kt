package com.chatflow.shared.domain

import java.time.Instant
import java.util.UUID

data class Chat(
    val id: UUID,
    val name: String?,
    val createdBy: UUID,
    val createdAt: Instant
)
