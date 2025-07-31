#!/bin/bash

# Prompt for module name
read -p "Enter your module name (e.g., product-service): " moduleName

# Check if module already exists
modulePath="$PWD/$moduleName"
if [ -d "$modulePath" ]; then
    echo -e "\n==== Module '$moduleName' already exists. Skipping creation."
    exit 1
fi

# Convert to PascalCase for class name
IFS='-' read -ra parts <<< "$moduleName"
classBase=""
for part in "${parts[@]}"; do
    classBase+="${part^}"
done
applicationClass="${classBase}Application"

# Paths
basePath="$modulePath"
javaBase="$basePath/src/main/java/com/raksa/app"
resourcesPath="$basePath/src/main/resources/templates"
resourcesPath1="$basePath/src/main/resources/static"

# Create directories
folders=(
    "$javaBase/config"
    "$javaBase/controllers"
    "$javaBase/dtos"
    "$javaBase/exceptions"
    "$javaBase/model"
    "$javaBase/repo"
    "$javaBase/services/lservices"
    "$javaBase/services/servicesImpl"
    "$javaBase/services/validation"
    "$javaBase/utils"
    "$javaBase/vo"
    "$resourcesPath"
    "$resourcesPath1"
)

for folder in "${folders[@]}"; do
    mkdir -p "$folder"
done

# Add module to settings.gradle if exists
settingsGradlePath="$PWD/settings.gradle"
moduleEntry="include '$moduleName'"

if [ -f "$settingsGradlePath" ]; then
    if ! grep -qF "$moduleEntry" "$settingsGradlePath"; then
        echo "$moduleEntry" >> "$settingsGradlePath"
        echo -e "\n==== Added '$moduleName' to settings.gradle"
    else
        echo -e "\n==== '$moduleName' already included in settings.gradle"
    fi
else
    echo -e "\n==== settings.gradle not found in root. Please create it manually and add: $moduleEntry"
fi

# Write Spring Boot main class
appFile="$javaBase/$applicationClass.java"
cat > "$appFile" <<EOF
package com.raksa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class $applicationClass {
    public static void main(String[] args) {
        SpringApplication.run($applicationClass.class, args);
    }
}
EOF

# Write application.yml
appYml="$resourcesPath/application.yml"
cat > "$appYml" <<EOF
server:
  port: 8080
spring:
  application:
    name: test
  cloud:
    discovery:
      enabled: true
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
EOF

# Write build.gradle
gradleFile="$basePath/build.gradle"
cat > "$gradleFile" <<EOF
dependencies {
    implementation project(':core')
}
EOF

# Create empty .gitignore
touch "$basePath/.gitignore"

# Summary
echo -e "\nProject structure for '$moduleName' created."
echo "==== Package: com.raksa.app"
echo "==== Application class: $applicationClass.java"
echo "==== application.yml and build.gradle configured."
