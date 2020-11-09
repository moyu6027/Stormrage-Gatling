FROM grafana/grafana:7.2.1-ubuntu

USER root

RUN apt-get update && apt-get install -y procps gettext-base && rm -rf /var/lib/apt/lists/*

WORKDIR /etc/grafana

COPY datasources ./datasources
COPY dashboard/dashboards.yaml /etc/grafana/provisioning/dashboards
COPY dashboard/gatling-dashboard.json /etc/grafana/provisioning/dashboards
COPY dashboard/gatlingNew.json /etc/grafana/provisioning/dashboards
COPY dashboard/gatlingTrend.json /etc/grafana/provisioning/dashboards
COPY dashboard/gatlingTrendWithLink.json /etc/grafana/provisioning/dashboards
COPY plugins /var/lib/grafana/plugins
COPY scripted_dashboards/ /usr/share/grafana/public/dashboards/

WORKDIR /app
COPY entrypoint.sh ./
RUN chmod u+x entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
