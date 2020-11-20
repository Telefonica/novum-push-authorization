@NonNullByDefault

/**
 * Service for composing and handling the user response
 * @version 1
 */
interface AuthQuestionService {
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