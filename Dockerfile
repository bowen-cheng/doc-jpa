FROM alpine:3.7

RUN apk update
RUN apk add bash curl unzip zip
RUN bash
RUN sdk install java
RUN apk --no-cache add ca-certificates && \
wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://raw.githubusercontent.com/sgerrand/alpine-pkg-glibc/master/sgerrand.rsa.pub && \
wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.25-r0/glibc-2.25-r0.apk && \
apk add glibc-2.25-r0.apk
RUN java -version

COPY . app
WORKDIR app
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "/build/libs/doc-jpa-0.0.1-SNAPSHOT.jar"]