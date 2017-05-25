## Twutils

A simple Java utility to search and retrieve tweets.

## Build

To create an executable jar file.

	mvn clean install assembly:single

## Options

Options available:

    --help           Show help.
    --output <out>   Output dir of tweets.
    --stweets <tt>   Collect tweets that contains the given keywords.


## Running

Pass any number of keywords the tweets you want to search must contain, separated by semicolon.

	java -jar --stweets "#Xfactor;#BakeOffBrazil;Estado Islâmico" --output dados_tv.csv	

## Contact

	https://www.linkedin.com/in/adail-carvalho-a34343106
	adail.dux@gmail.com