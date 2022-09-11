package au.com.seek.aips.challenge;

import org.junit.jupiter.api.Test;

class ChallengeApplicationTests {

	/**
	 * This method doesn't test anything. Its only purpose is to launch the application as a test.
	 */
	@Test
	void kickoff() {
		ChallengeApplication application = new ChallengeApplication();
		application.startingJob("src/test/resources/input/data.txt");
	}

}
