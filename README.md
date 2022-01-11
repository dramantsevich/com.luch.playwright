# com.luch.playwright

# rewritten framework for playwright

# Run from command line:
mvn -Dbrowser='browserName' -Dsurefire.suiteXmlFiles=src\test\resources\'testsuite.xml' clean test

# Example:
mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-parallel.xml clean test

# To run allure-report:
to command line: allure serve target/allure-results
