FROM  katappultplatform/base-jdk21
ARG JAR_FILE=target
#ARG JAR_FILE=target\*.jar
ENV BOT_NAME=QQQQQEGE
ENV BOT_TOKEN=GBEEGER
ENV BOT_DB_USERNAME=jrtb-db
ENV BOT_DB_PASSWORD=ROOT
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-DBOT_NAME=${BOT_NAME}","-DBOT_TOKEN=${BOT_TOKEN}","-jar","/app.jar","-Dspring.datasource.password=${BOT_DB_PASSWORD}","-Dbot_username=${BOT_NAME}"]