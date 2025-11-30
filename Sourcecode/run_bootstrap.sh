#!/bin/bash

# Create logs directory
mkdir -p logs

# Compile all sources to ensure everything is available
echo "Compiling all sources..."
# We use find to get all java files because compiling just the loader might miss runtime-only dependencies (like Hibernate mappings)
find src -name "*.java" > sources.txt
javac -cp "WebContent/WEB-INF/lib/*" -sourcepath src @sources.txt

if [ $? -eq 0 ]; then
    echo "Compilation successful. Running BootstrapDataLoader..."
    # Run with classpath including libs, source (for compiled classes), and resources (for config)
    # Set catalina.home to current directory so logs go to ./logs/omds.log
    java -Dcatalina.home=. -cp "WebContent/WEB-INF/lib/*:src:resources" com.indven.tools.bootstrap.BootstrapDataLoader
else
    echo "Compilation failed."
fi
rm sources.txt
