
Dependencies Requisite: 
1.Ensure Java installed and JAVA_HOME is set:  echo $JAVA_HOME
2.Ensure Maven Installed : mvn -version
3.Ensure XCOde/ CommandLine tools are installed

From Folder:  WooliesXCodeChallenge

1.mvn clean install 
2.Open the project in IntelliJ Configure below 
    A. In Project settings - select the JDK Version.
    B. Add plug In - Cucumber For Java , Gherkin , Lombok 
    
Appium Setup : 
Install Appium desktop : 1.17.1-1 from ( https://github.com/appium/appium-desktop/releases/tag/v1.17.1-1)

To Run the test : 
1. Open the Appium desktop - Start server 
2. Open the xcode and Run Simulator ( for example )- To run on your desired Simulator - Update the capability
in IOSDriverFactory wih your platform name  and platform version.  
2. Open the project in IntelliJ - Run Class "InvokeTests.java " 
                                  PATH - /src/test/java/au/com/ 
Reports and Screen shots :
 ..\build\cucumber\ExtendedReport
 ../screenshots/

