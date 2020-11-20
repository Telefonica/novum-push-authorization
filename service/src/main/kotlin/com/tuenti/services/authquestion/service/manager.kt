package com.tuenti.services.authquestion.service

import javax.inject.Inject

class AuthQuestionManager @Inject constructor(
        private val storage: AuthQuestionStorage
) {

    fun storeRequest(request: UserQuestionRequestEvent) {
        storage.storeRequest(request);
    }

    fun getRequest(transactionId: String) : UserQuestionRequestEvent? {
        return storage.getRequest(transactionId)
    }

    fun setResponse(transactionId: String, statement: String) {
        //TODO notify an async job to send the response to 4P
    }
}