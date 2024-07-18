FROM gcr.io/distroless/java17@sha256:009deffea52dc93a7563fd73ff55138fa02cdabe32c4defa8375ce1cee86ac4a
EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


