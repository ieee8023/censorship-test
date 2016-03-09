mkdir -p classes
javac -cp .  -d classes `find src -type f -name "*.java"`
