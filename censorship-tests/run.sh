#!/bin/bash

java -Xmx4g -cp `sh getclasspath.sh`:classes TCPConnectTest $@

echo java finished