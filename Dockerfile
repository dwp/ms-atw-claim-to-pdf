FROM gcr.io/distroless/java11@sha256:a83115dc78baf90e1ae41bf6eaa2bfe1e5b0f8afb91bd7330a488d413322fcb2
EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


