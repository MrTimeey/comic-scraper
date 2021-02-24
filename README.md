# Comic Scraper [![Build Status](https://travis-ci.org/MrTimeey/comic-scraper.svg?branch=master)](https://travis-ci.org/MrTimeey/comic-scraper)

The comic scraper is a backend application which collects information of comic series and their new releases. It's also planned to generate a custom newsletter.
Besides that it's a learning project for various technologies.

## Local development
For local development you need to execute the following steps.

### Manual way
1. Create '.env' file in root directory
```shell
# Security
SECURITY_USER=...
SECURITY_PASSWORD=...
MONGO_PASS=...
MONGO_ROOT_PASS=...
SPRING_MAIL_USER=...
SPRING_MAIL_PASS=...
```
2. Start mongo db via docker-compose
```shell
docker-compose --file docker-compose.yml --file docker-compose-local.yml up mongo-database
```
3. Run maven clean install
```shell
mvn clean install
```
4. Start application
4.1 Plain start
```shell
java -Dspring.profiles.active=local -jar target/comic-scraper-0.1.0-SNAPSHOT.jar
```
4.2 Start with setting passwords
```shell
java -Dspring.profiles.active=local --MONGODB_PASSWORD=<pass> --MAIL_PASSWORD=<pass>  -jar  target/comic-scraper-0.1.0-SNAPSHOT.jar
```

### IDE
1. Start mongo db via docker-compose
```shell
docker-compose --file docker-compose.yml --file docker-compose-local.yml up mongo-database
```
2. Define run configuration in IDE
```shell
mainClass: de.mrtimeey.comicscraper.ComicScraperApplication
Programm Arguments: --MONGODB_PASSWORD=<pass> --MAIL_PASSWORD=<mail-pass> --MAIL_USER=<mail-user>
Active profile: local
```
3. Execute run configuration in IDE
Run the defined configuration.