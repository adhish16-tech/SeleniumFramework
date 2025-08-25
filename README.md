Selenium Automation Framework 🚀

This repository contains a robust Selenium Test Automation Framework built with Java, TestNG, and Page Object Model (POM) design. It is designed for scalability, reusability, and easy maintenance, while also integrating reporting and CI/CD readiness.

🔑 Features
	•	Java + Selenium WebDriver based automation
	•	Page Object Model (POM) design pattern
	•	TestNG for test execution and parallel runs
	•	Data-driven testing (JSON & Excel)
	•	Retry logic for flaky test handling
	•	Extent Reports for rich test reporting
	•	Slack Notifications for test results
	•	Reusable utilities for easy test management

📂 Project Structure

SeleniumFramework/  
│── src/main/java/        # Page Objects, Utilities  
│── src/test/java/        # Test Cases  
│── reports/              # Test Reports  
│── testng.xml            # TestNG Suite File  
│── pom.xml               # Maven Configuration  

⚡ How to Run Tests

Prerequisites
	•	Install Java 11+
	•	Install Maven
	•	Clone this repo:

git clone https://github.com/adhish16-tech/SeleniumFramework.git  
cd SeleniumFramework  

Run from Terminal

mvn clean test  

Run Specific Suite

mvn clean test -DsuiteXmlFile=testng.xml  

📊 Reports
	•	Extent Reports generated under reports/
	•	Screenshots on failure
	•	Slack notifications supported

🚀 CI/CD Integration

This framework can be easily integrated with:
	•	Jenkins
	•	GitHub Actions
	•	GitLab CI/CD
	•	Azure DevOps

📬 Author

👤 Adhish Singh
	•	QA Automation Engineer
	•	Expertise: Selenium, API Testing, Blockchain, AI Testing
	•	GitHub: adhish16-tech

