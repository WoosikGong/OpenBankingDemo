FROM azul/zulu-openjdk:18
ARG WAR_FILE=/target/OpenBankPayDemo-1.0.2-SNAPSHOT.war
COPY ${WAR_FILE} app.war
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.war"]