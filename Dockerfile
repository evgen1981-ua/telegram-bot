FROM  katappultplatform/base-jdk21
ARG JAR_FILE=target\*.jar
ENV BOT_NAME=QQQQQEGE
ENV BOT_TOKEN=GBEEGER
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-DBOT_NAME=${BOT_NAME}","-DBOT_TOKEN=${BOT_TOKEN}","-jar","/app.jar"]