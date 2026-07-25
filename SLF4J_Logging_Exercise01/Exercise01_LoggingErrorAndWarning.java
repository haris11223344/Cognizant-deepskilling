import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise01_LoggingErrorAndWarning {

    private static final Logger logger =
            LoggerFactory.getLogger(Exercise01_LoggingErrorAndWarning.class);

    public static void main(String[] args) {

        logger.info("Application Started");

        int age = 15;

        if (age < 18) {
            logger.warn("User is below 18 years old.");
        }

        try {
            int result = 100 / 0;
            logger.info("Result: {}", result);
        } catch (ArithmeticException e) {
            logger.error("Exception occurred while performing division.", e);
        }

        logger.info("Application Finished");
    }
}
