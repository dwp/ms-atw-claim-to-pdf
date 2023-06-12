FROM gcr.io/distroless/java11@sha256:ca5414375b5d8a5889d2a46051b42630e93c6237a15a3c83a948adbb92572e13
EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


