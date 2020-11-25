package com.tuenti.services.authquestion.service

import com.tuenti.services.authquestion.service.v1.AuthQuestionService
import com.tuenti.services.authquestion.service.v1.serviceCodes
import com.tuenti.services.authquestion.service.v1.dto.AuthQuestionRequest
import com.tuenti.services.authquestion.service.v1.dto.EventDto
import com.tuenti.services.authquestion.service.v1.exceptions.AuthQuestionRequestNotFound
import com.tuenti.tservice.TServiceExport
import com.tuenti.tservice.app.BaseServiceImplementation
import javax.inject.Inject

@TServiceExport(iface = AuthQuestionService::class)
class AuthQuestionServiceImpl @Inject constructor(
        private val authQuestionManager : AuthQuestionManager
) : BaseServiceImplementation(), AuthQuestionService {

    override fun onAuthQuestionRequest(event: EventDto) =
            authQuestionManager.storeAndForwardRequest(event)

    override fun getQuestionRequest(transactionId: String): AuthQuestionRequest =
            authQuestionManager.getRequest(transactionId)?.toAuthQuestionRequestDto()
                    ?: throw AuthQuestionRequestNotFound("auth question request not found")

    override fun setQuestionResponse(transactionId: String, statement: String) =
            authQuestionManager.setResponse(transactionId, statement)

    override fun getServiceErrorCodes(): MutableMap<Class<*>, Int> =
            serviceCodes.toMutableMap()

    private fun UserQuestionRequestEvent.toAuthQuestionRequestDto(): AuthQuestionRequest =
            AuthQuestionRequest(transactionId, question, statements)
}