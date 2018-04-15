FROM centos

RUN yum install -y curl zip unzip which
RUN curl -s "https://get.sdkman.io" | bash \
 && source /root/.sdkman/bin/sdkman-init.sh \
 && sdk install java \
 && sdk use java \
 && which java

COPY src app/src
COPY *gradle* app/

ENV JAVA_HOME /root/.sdkman/candidates/java/current

WORKDIR app
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "./build/libs/doc-jpa-0.0.1-SNAPSHOT.jar"]
