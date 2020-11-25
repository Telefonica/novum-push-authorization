@NonNullByDefault

/**
 * Service for receiving request and composing the user response
 * @version 1
 */
interface AuthQuestionService {
	/**
	 * Method to process a user question request (comms-like event)
	 * @param event
	 * @throws TServiceException
	 */
	void onAuthQuestionRequest(EventDto event);

	/**
	 * Method to get all the question data in a push-authorization request
	 *
	 * @return QuestionRequest
	 *
	 */
	AuthQuestionRequest getQuestionRequest(String transactionId) throws AuthQuestionRequestNotFound;

	/**
	 * Set the user response for a push-authorization request
	 *
	 * @param transactionId Transaction id for the response
	 * @param statement Statement or option selected by the user
	 */
	void setQuestionResponse(String transactionId, String statement);
}

/**
 * Question request for user to confirm the authorization
 */
class AuthQuestionRequest {
	String transactionId;
	String question;
	String[] statements;
}

class AuthQuestionRequestNotFound extends Exception {
	int code = 100;
}

class EventDto {
	String eventId;
	ParamDto[] params;
}

class ParamDto {
	String name;
	String value;
}
