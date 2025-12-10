package com.chatflow.shared.domain

import java.time.Instant
import java.util.UUID

data class ChatParticipant(
    val chatId: UUID,
    val userId: UUID,
    val role: ParticipantRole,
    val joinedAt: Instant
)
