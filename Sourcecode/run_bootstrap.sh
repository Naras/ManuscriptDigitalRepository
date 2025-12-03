#!/bin/bash

show_help() {
    echo "Usage: ./run_bootstrap.sh [login_id] [password]"
    echo ""
    echo "Arguments:"
    echo "  login_id    Optional. The administrator login ID (email)."
    echo "  password    Optional. The administrator password."
    echo ""
    echo "If no arguments are provided, the default credentials will be used."
    echo ""
    echo "Examples:"
    echo "  ./run_bootstrap.sh"
    echo "  ./run_bootstrap.sh admin@example.com mysecretpassword"
}

# Check for help arguments
if [[ "$1" == "--help" || "$1" == "-h" ]]; then
    show_help
    exit 0
fi

# Check for no arguments and prompt for confirmation
if [ $# -eq 0 ]; then
    echo "default credentials - login id: CTO@Samskriti.org pw: CTO123$%^ Please confirm (yes/no)"
    read response
    # Check if response starts with n or N
    if [[ "$response" =~ ^[Nn] ]]; then
        show_help
        exit 0
    fi
fi

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
    java -Dcatalina.home=. -cp "WebContent/WEB-INF/lib/*:src:resources" com.indven.tools.bootstrap.BootstrapDataLoader "$@"
else
    echo "Compilation failed."
fi
rm sources.txt
