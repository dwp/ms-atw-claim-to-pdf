FROM gcr.io/distroless/java17@sha256:f5aebb97006043234d341e3c7eacb063b0abefb86a51a238c07a38ac6f298dd9

USER nonroot

COPY --from=pik94420.live.dynatrace.com/linux/oneagent-codemodules:java / /
ENV LD_PRELOAD /opt/dynatrace/oneagent/agent/lib64/liboneagentproc.so

EXPOSE 9015

COPY styles/ /styles/
COPY target/ms-claim-to-pdf-*.jar /ms-claim-to-pdf.jar

HEALTHCHECK --interval=30s --timeout=5s CMD curl -f http://localhost:9015/healthcheck || exit 1

ENTRYPOINT ["java", "-jar",  "/ms-claim-to-pdf.jar"]


