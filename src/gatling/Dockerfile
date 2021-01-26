FROM centos
ENV LANG C.UTF-8
ENV GATLING_VERSION 3.4.2

WORKDIR /

RUN yum install -y java-1.8.0-openjdk wget unzip

RUN wget https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/${GATLING_VERSION}/gatling-charts-highcharts-bundle-${GATLING_VERSION}-bundle.zip && \
    unzip gatling-charts-highcharts-bundle-${GATLING_VERSION}-bundle.zip

RUN mv gatling-charts-highcharts-bundle-${GATLING_VERSION} gatling