# JPetStore 6 on Apache TomEE
JPetStore 6 is a full web application built on top of MyBatis 3, Spring 3 and Stripes. It is available for downloading in the downloads section of MyBatis project site. See: http://www.mybatis.org/spring/sample.html

This version has been modified to include Java EE 6 features in order to demonstrate a transition from [Apache Tomcat 7.x](http://tomcat.apache.org/) to [Apache TomEE](http://tomee.apache.org/index.html)
It is in no way a best practice application in regards to Java EE, rather a fully working pre-EE application running within an EE enabled environment.

## Running sample under eclipse
Follow the next steps to run the sample on [Apache TomEE](http://tomee.apache.org/index.html), by using eclipse IDE
 - Download and install a JDK, eclipse and [Apache TomEE](http://tomee.apache.org/index.html)
 - Run Eclipse
 - In eclipse, go to Help/Eclipse Marketplace
 - Install m2e plugin (maven)
 - Install m2e-wtp plugin (maven for wtp)
 - Check out the project from Git and import it into the workspace
 - A new project named "jpetstore-6" will be shown
 - Right click on jpetstore project and click on configure/convert to maven project
 - Right click on jpetstore project and select "run on server"
 - Select [Apache TomEE](http://tomee.apache.org/index.html) Server and set your installation directory
 - JPetStore home page should be shown

## Running with Apache TomEE
Running JPetStore sample under [Apache TomEE](http://tomee.apache.org/index.html) (using maven).
 - Clone this repository
 - Open command prompt/shell and change to cloned directory
 - Issue following command to run project using [Apache TomEE](http://tomee.apache.org/index.html)

    mvn clean tomee:run

 - Run application in browser http://localhost:8080/jpetstore/ 
 - Press Ctrl-C to stop the server.
