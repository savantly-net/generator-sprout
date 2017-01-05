# <%= appname %>


## Run
pre-requisites
* java
	
	$ java -jar <%= appname %>.jar
	
You can place an `application.properties` file in the same directory as the jar to override default properties.
For example -

<pre> 
Directory of C:\temp\example 
   application.properties 
   <%= appname %>.jar 
</pre> 

or you can create a properties file for different profiles by passing the command line parameter -

<pre> 
Directory of C:\temp\example 
   application.properties 
   application-prod.properties 
   <%= appname %>.jar 
</pre> 

    $ java -jar <%= appname %>.jar --spring.profiles.active=development


## Build  
pre-requisites
* nodejs, bower, maven, java

    $ mvn package

	