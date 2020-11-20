package com.tuenti.services.authquestion.service

import com.tuenti.platform.memcached.MemcacheService
import javax.inject.Inject

class AuthQuestionStorage @Inject constructor (
        val memcached: MemcacheService
) {
    fun storeRequest(request: UserQuestionRequestEvent) =
            memcached.set(getKey(request.transactionId), request, TTL_DEFAULT)

    fun getRequest(transactionId: String) : UserQuestionRequestEvent? =
            memcached.get(getKey(transactionId), UserQuestionRequestEvent::class.java)

    fun deleteRequest(transactionId: String) =
            memcached.delete(getKey(transactionId))

    private fun getKey(transactionId: String) =
            "auth-question-${transactionId}"

    companion object {
        const val TTL_DEFAULT = 120;
    }
}
