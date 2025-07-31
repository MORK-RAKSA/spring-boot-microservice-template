# Prompt for module name
$moduleName = Read-Host "Enter your module name (e.g., product-service)"

# Check if module already exists
$modulePath = "$PWD\$moduleName"
if (Test-Path $modulePath) {
    Write-Host "`n`==== Module '$moduleName' already exists. Skipping creation."
    return
}

# Convert to PascalCase for class name
$classBase = ($moduleName -split '-').ForEach({ $_.Substring(0,1).ToUpper() + $_.Substring(1) }) -join ''
$applicationClass = "${classBase}Application"

# Paths
$basePath = "$PWD\$moduleName"
$javaBase = Join-Path $basePath "src\main\java\com\raksa\app"
$resourcesPath = "$basePath\src\main\resources"

# Create directories
$folders = @(
    "$javaBase\config",
    "$javaBase\controllers",
    "$javaBase\dtos",
    "$javaBase\exceptions",
    "$javaBase\model",
    "$javaBase\repo",
    "$javaBase\services\lservices",
    "$javaBase\services\servicesImpl",
    "$javaBase\services\validation",
    "$javaBase\utils",
    "$javaBase\vo",
    "$resourcesPath"
)

foreach ($folder in $folders) {
    New-Item -ItemType Directory -Force -Path $folder | Out-Null
}

# Add module to settings.gradle if not already included
$settingsGradlePath = "$PWD\settings.gradle"
$moduleEntry = "include '$moduleName'"

if (Test-Path $settingsGradlePath) {
    $content = Get-Content $settingsGradlePath
    if ($content -notcontains $moduleEntry) {
        Add-Content -Path $settingsGradlePath -Value $moduleEntry
        Write-Host "`n`==== Added '$moduleName' to settings.gradle"
    } else {
        Write-Host "`n`==== '$moduleName' already included in settings.gradle"
    }
} else {
    Write-Host "`n`==== settings.gradle not found in root. Please create it manually and add: $moduleEntry"
}


# Write Spring Boot main class
$appFile = "$javaBase\$applicationClass.java"
@"
package com.raksa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class $applicationClass {
    public static void main(String[] args) {
        SpringApplication.run($applicationClass.class, args);
    }
}
"@ | Set-Content -Path $appFile

# Write application.yml
$appYml = "$resourcesPath\application.yml"
@"
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
"@ | Set-Content -Path $appYml

# Write build.gradle
$gradleFile = "$basePath\build.gradle"
@"
dependencies {
    implementation project(':core')
}
"@ | Set-Content -Path $gradleFile



# Create empty .gitignore
New-Item -ItemType File -Path "$basePath\.gitignore" -Force | Out-Null

Write-Host "`nProject structure for '$moduleName' created."
Write-Host "`n`==== Package: com.raksa.app"
Write-Host "`n`==== Application class: $applicationClass.java"
Write-Host "`n`==== application.yml and build.gradle configured.`n` "
