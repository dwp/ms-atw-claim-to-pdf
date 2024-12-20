FROM gcr.io/distroless/java17@sha256:c9400462390c919bdcbba1fd233a862af649a99e06ad6314ca9b9fbb32ebbca2

COPY --from=pik94420.live.dynatrace.com/linux/oneagent-codemodules:java / /
ENV LD_PRELOAD /opt/dynatrace/oneagent/agent/lib64/liboneagentproc.so

EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar
ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


