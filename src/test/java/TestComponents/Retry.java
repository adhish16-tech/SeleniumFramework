package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer	{
	int count=0;
	int maxTry=1;
	

	@Override
	public boolean retry(ITestResult result) {
		
		
		// TODO Auto-generated method stub
		if(count<maxTry) {
			count++;
			System.out.println("Retrying " + result.getName() + " for " + count + " time(s).");
			return true;
		}
	 else {
        result.setStatus(ITestResult.FAILURE); // Force FAIL after retries
        System.out.println("Marking " + result.getName() + " as FAILED after retries.");
	}
		return false;
	

}
}
