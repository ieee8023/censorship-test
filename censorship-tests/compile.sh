mkdir -p classes
javac -target 1.7 -source 1.7 -cp `sh getclasspath.sh` -d classes `find src -type f -name "*.java"`
