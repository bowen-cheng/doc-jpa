FROM centos

RUN yum install -y curl zip unzip which
RUN curl -s "https://get.sdkman.io" | bash \
 && source /root/.sdkman/bin/sdkman-init.sh \
 && sdk install java \
 && sdk use java \
 && sdk install gradle \
 && sdk use gradle

COPY src app/src

ENV JAVA_HOME /root/.sdkman/candidates/java/current

WORKDIR app
RUN gradle build
ENTRYPOINT ["java", "-jar", "./build/libs/doc-jpa-0.0.1-SNAPSHOT.jar"]
