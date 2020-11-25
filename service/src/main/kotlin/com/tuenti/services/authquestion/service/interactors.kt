package com.tuenti.services.authquestion.service

import com.tuenti.services.commsToolSender.v1.CommsToolSenderProvider
import com.tuenti.services.authquestion.service.v1.dto.EventDto as IncomingEventDto
import com.tuenti.services.commsToolSender.v1.dto.EventDto
import com.tuenti.services.commsToolSender.v1.dto.ParamDto
import javax.inject.Inject

class CommsToolInteractor @Inject constructor(
        private val commsToolSenderProvider: CommsToolSenderProvider
) {
    fun send(request: IncomingEventDto) {
        commsToolSenderProvider.get().executeEvent(
                EventDto(request.eventId, request.params.map { ParamDto(it.name, it.value) })
        )
    }
}
