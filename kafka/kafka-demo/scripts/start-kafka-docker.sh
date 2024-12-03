#!/bin/bash

set -x

docker run -d --name broker apache/kafka:latest


# docker exec --workdir /opt/kafka/bin/ -it broker sh

