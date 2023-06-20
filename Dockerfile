FROM gcr.io/distroless/java11@sha256:e65ff03cf2bee3e2ea2a3fd26a49e5595be4f8d3df0e34454f32b06fc7a83753
EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


