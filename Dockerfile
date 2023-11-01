FROM gcr.io/distroless/java11@sha256:e97b56c50a651ec41a1e40cf2f8f96eb7ce4a4f42e1094e8462ab47a949c79e9
EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


