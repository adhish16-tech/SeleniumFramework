package TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SlackTestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        SlackNotifier.sendMessage("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        SlackNotifier.sendMessage("❌ Test Failed: " + result.getName() +
                "\nError: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        int retried = Retry.totalRetries;  // ✅ use retry count
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";
        String message = "📊 *Test Suite Summary:*\n" +
                         "✅ Passed: " + passed + "\n" +
                         "❌ Failed: " + failed + "\n" +
                         "⚠️ Skipped: " + skipped + "\n" +
                         "🔄 Retried: " + retried+ "\n" +
                         "📑 *Full Report:* <file://" + reportPath + "|Open Report>";

        SlackNotifier.sendMessage(message);
    }
}