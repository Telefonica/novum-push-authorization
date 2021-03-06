package com.tuenti.services.authquestion.service

data class UserQuestionRequestEvent (
        val transactionId: String,
        val identifierType: String,
        val identifierId: String,
        val question: String,
        val statements: List<String>,
        val amr: String,
        val maxAge: Int,
        val expirationDateTimestamp: Long,
        val notificationEventId: String,
        val creationDateTimestamp: Long,
        val userId4p: String?,
        val msisdn: String?
)

data class UserQuestionResponseEvent (
        val transactionId: String,
        val statement: String,
        val amr: String,
        val responseType: String,
        val notificationEventId: String,
        val creationDateTimestamp: Long,
        val userId4p: String?,
        val msisdn: String?
)
