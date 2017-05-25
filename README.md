## Twutils

A simple Java utility to search and retrieve tweets.

## Requirements

- JDK 7 or Above. Download Page: http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html . After the download is complete, install JDK and set the JAVA_HOME system variable, pointed to your JDK bin directory.

- Maven 3. Download Page: https://maven.apache.org/install.html. After the download is complete, set the MAVEN_HOME system variable, pointing to your Maven bin directory. 

- Twitter OAuth Security page: https://dev.twitter.com/. Before using this app, create your Access Tokens, to authorize the application to consume the Twitter Search API. Otherwise, it won't be able to connect it. After creating your tokens, go to - > src\main\resources\social_networking.properties , and set then on the corresponding properties:

		twutils.twitter.access.token=YourAccessToken
	
		twutils.twitter.access.secret=YourAccesSecret
	
		twutils.twitter.consumer.key=YourConsumerKey
	
		twutils.twitter.consumer.secret=YourConsumerSecret

## Build

Run the bellow command inside the project directory to create an executable jar file. After completing succesfully, the jar will be deployed on the "target" dir. 

	mvn clean install assembly:single

## Options

Options available:

    --help           Show help.
    --output <out>   Output directory to write the collected tweets.
    --stweets <tt>   Collect tweets that contains the given keywords.


## Running

Pass any number of keywords the tweets you want to search must contain, separated by semicolon. The default output directory will 
- Windows: C:\opt\twutils\
- Linux: /opt/twutils/

	java -jar --stweets "#Xfactor;#BakeOffBrazil;Estado Islâmico" --output dados_tv.csv	

## Contact

	https://www.linkedin.com/in/adail-carvalho-a34343106
	adail.dux@gmail.com