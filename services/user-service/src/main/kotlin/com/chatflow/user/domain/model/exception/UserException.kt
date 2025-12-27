package com.chatflow.user.domain.model.exception

import java.util.UUID

/**
 * Clase base sellada para todas las excepciones del dominio de usuario.
 */
sealed class UserException(message: String) : RuntimeException(message)

// Excepciones de Usuario
class UserNotFoundException(identifier: String) :
    UserException("User not found: $identifier")

class UserAlreadyExistsException(email: String) :
    UserException("User already exists with email: $email")

class UserNotVerifiedException(userId: UUID) :
    UserException("User is not verified: $userId")

class InvalidCredentialsException :
    UserException("Invalid email or password")

// Excepciones de Token
class InvalidTokenException(reason: String) :
    UserException("Invalid token: $reason")

class TokenExpiredException(tokenType: String) :
    UserException("$tokenType has expired")

class TokenAlreadyUsedException(tokenType: String) :
    UserException("$tokenType has already been used")

// Excepciones de Rate Limiting
class RateLimitExceededException(
    val retryAfterSeconds: Long,
    resource: String
) : UserException("Rate limit exceeded for $resource. Retry after $retryAfterSeconds seconds")

// Excepciones de Validación
class ValidationException(message: String) : UserException(message)

class WeakPasswordException :
    UserException("Password does not meet security requirements")