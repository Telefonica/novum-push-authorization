@NonNullByDefault

/**
 * Agent to handle authorization questions in apps
 * @version 1
 */
interface AuthQuestionAgent {

	/**
	 * Method to get all the question data in a push-authorization request
	 *
	 * @return QuestionRequest
	 *
	 */
	AuthQuestionRequest getQuestionRequest(String transactionId);

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
