package com.tuenti.services.authquestion.service

import com.tuenti.services.authquestion.service.v1.dto.EventDto
import javax.inject.Inject

class AuthQuestionManager @Inject constructor(
        private val storage: AuthQuestionStorage,
        private val commsToolInteractor: CommsToolInteractor
) {

    fun storeAndForwardRequest(request: EventDto) {
        storage.storeRequest(request.toUserQuestionRequestEvent())
        commsToolInteractor.send(request)
    }

    fun getRequest(transactionId: String) : UserQuestionRequestEvent? {
        return storage.getRequest(transactionId)
    }

    fun setResponse(transactionId: String, statement: String) {
        //TODO notify an async job to send the response to 4P
    }

    private fun EventDto.toUserQuestionRequestEvent(): UserQuestionRequestEvent {
        val params = params.map { it.name to it.value }.toMap()

        // Identification check
        if (params["event.user_id_4p"].isNullOrEmpty() && params["event.msisdn"].isNullOrEmpty()) {
            throw Exception("event must include user_id_4p or msisdn")
        }

        return UserQuestionRequestEvent(
                transactionId = params["event.transaction_id"]
                        ?: throw Exception("transaction_id param not defined"),
                identifierId = params["event.identifierId"]
                        ?: throw Exception("identifier_id param not defined"),
                identifierType = params["identifier_type"]
                        ?: throw Exception("identifier_type param not defined"),
                question = params["event.question"]
                        ?: throw Exception("question param not defined"),
                statements = params["event.statements"]?.split(",")
                        ?: throw Exception("statements param not defined"),
                amr = params["event.amr"]
                        ?: throw Exception("amr not defined"),
                maxAge = params["event.max_age"]?.toInt()
                        ?: throw Exception("max_age param not defined"),
                expirationDateTimestamp = 0,  //TODO expiration Date to timestamp
                notificationEventId = params["event.notification_event_id"]
                        ?: throw Exception("notification_event_id not defined"),
                creationDateTimestamp = 0,  //TODO to creationDate timestamp
                userId4p = params["event.user_id_4p"],
                msisdn = params["event.msisdn"]
        )
    }
}