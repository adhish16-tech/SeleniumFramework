
# Selenium Framework Design (Java)
A robust Selenium Automation Testing Framework built with Java, TestNG, and Maven, following Page Object Model (POM) principles. This framework is designed for scalable, maintainable, and reusable automated test execution.

Features:
- Page Object Model (POM) with reusable components
- Data-driven testing (JSON/Excel support)
- Maven build management
- TestNG integration for test execution and reporting
- Retry Logic for flaky tests
- Cross-browser support (Chrome, Firefox, Edge)
- CI/CD ready (Jenkins, GitHub Actions)
- Detailed Reports (Extent/Surefire Reports)

Project Structure:
SeleniumFrameworkDesign
 ├── src/main/java          # Page Objects + Utilities
 ├── src/test/java          # Test classes
 ├── test-output/           # Reports
 ├── pom.xml                # Maven dependencies
 └── README.md              # Documentation

Getting Started:
1. Prerequisites:
   - Java 11+
   - Maven 3.6+
   - TestNG plugin
   - Git

2. Clone Repository:
   git clone https://github.com/adhish16-tech/SeleniumFramework_Java.git
   cd SeleniumFrameworkDesign

3. Install Dependencies:
   mvn clean install

4. Run Tests:
   mvn test

Reporting:
- TestNG default reports under `test-output/`
- Extent Reports for detailed execution results

Integration:
- Supports CI/CD pipelines with Jenkins / GitHub Actions
- Extendable for Docker/Selenium Grid

Author:
Adhish Singh – QA Automation Engineer
LinkedIn: https://www.linkedin.com/in/adhish-singh/
