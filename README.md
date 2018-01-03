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
    --streamTT <tt>  Collect tweets that contains the given keywords.
    --geoTT <geo>    Filter tweets by coordinates.
    --unit <unit>    Measure unit that will be applied over the given radius.


## Running

Pass any number of keywords the tweets you want to search must contain, `separated by comma`: 

	java -jar twutils-1.0.0-jar-with-dependencies.jar --streamTT "#Xfactor, #BakeOffBrazil, Estado Islâmico" --output dados_tv.csv
	
If you want to retrive tweets from a specific region, provide the coordinates and radius you want 
the search mechanism to filter through the geoTT option, `delimited by comma`, in the folowing order:
*latitude, longitude, radius*

	java -jar twutils-1.0.0-jar-with-dependencies.jar --streamTT "samsung" --geoTT  "-3.737021, -38.483637, 0" --unit "KM" --output dados_tv.csv

The default output directory will be:

- On **Windows**: 

	C:\opt\twutils\

- On **Linux**: 

	/opt/twutils/

	

## Contact

Contact me through [LinkedIn](https://www.linkedin.com/in/adail-carvalho-a34343106).
