package com.chatflow.shared.domain

import java.time.Instant
import java.util.UUID

data class Message(
    val id: UUID,
    val chatId: UUID,
    val senderId: UUID,
    val content: String,
    val createdAt: Instant,
    val updatedAt: Instant?
)