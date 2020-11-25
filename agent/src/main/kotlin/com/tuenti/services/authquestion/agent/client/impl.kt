package com.tuenti.services.authquestion.agent.client

import com.tuenti.services.authquestion.agent.client.v1.AuthQuestionServiceProvider
import com.tuenti.services.authquestion.agent.client.v1.dto.AuthQuestionRequest as AuthQuestionRequestFromServer
import com.tuenti.services.authquestion.agent.client.v1.exceptions.AuthQuestionRequestNotFound
import com.tuenti.services.authquestion.agent.client.v1.exceptions.AuthQuestionRequestNotFound as ServerRequestNotFound
import com.tuenti.services.authquestion.agent.v1.AuthQuestionAgent
import com.tuenti.services.authquestion.agent.v1.dto.AuthQuestionRequest
import com.tuenti.services.authquestion.agent.v1.serviceCodes
import com.tuenti.tservice.TServiceExport
import com.tuenti.tservice.app.BaseServiceImplementation
import javax.inject.Inject

@TServiceExport(iface = AuthQuestionAgent::class)
class AuthQuestionServiceImpl @Inject constructor(
        private val serviceProvider : AuthQuestionServiceProvider
) : BaseServiceImplementation(), AuthQuestionAgent {
    override fun getQuestionRequest(transactionId: String) : AuthQuestionRequest =
            serviceExceptionMapper {
                serviceProvider.get().getQuestionRequest(transactionId).toAgent()
            }

    override fun setQuestionResponse(transactionId: String, statement: String) =
            serviceExceptionMapper {
                serviceProvider.get().setQuestionResponse(transactionId, statement)
            }

    override fun getServiceErrorCodes(): MutableMap<Class<*>, Int> = serviceCodes.toMutableMap()

    private inline fun <reified T> serviceExceptionMapper(action: () -> T): T {
        try {
            return action()
        } catch (e: ServerRequestNotFound) {
            throw AuthQuestionRequestNotFound(e.message ?: "error general")
        }
    }

    private fun AuthQuestionRequestFromServer.toAgent(): AuthQuestionRequest =
        AuthQuestionRequest(transactionId, question, statements)
}
