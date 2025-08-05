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
$templatesPath = Join-Path $basePath "src\main\resources\templates"
$staticPath    = Join-Path $basePath "src\main\resources\static"
$resourcesPath = Join-Path $basePath "src\main\resources"


# Create directories
$folders = @(
    "$javaBase\caches",
    "$javaBase\config",
    "$javaBase\controllers",
    "$javaBase\dtos",
    "$javaBase\dtos\requests",
    "$javaBase\dtos\responses",
    "$javaBase\exceptions",
    "$javaBase\model",
    "$javaBase\repository",
    "$javaBase\services\lservices",
    "$javaBase\services\servicesImpl",
    "$javaBase\services\validation",
    "$javaBase\utils",
    "$javaBase\vo",
    "$templatesPath",
    "$staticPath",
    "$resourcesPath"

)

foreach ($folder in $folders) {
    New-Item -ItemType Directory -Force -Path $folder | Out-Null
}

# Add module to settings.gradle if not already included
$settingsGradlePath = "$PWD\settings.gradle"
$moduleEntry = "include '$moduleName'"

# Add new route to gateway's application.yml
$gatewayYmlPath = "$PWD\gateway\src\main\resources\application.yml"

if (Test-Path $gatewayYmlPath) {
    $routeBlock = @"
        - id: $moduleName
          uri: lb://$moduleName
          predicates:
            - Path=/api-app/v1.0.0/$moduleName/**
          filters:
            - StripPrefix=3
"@
    $appYmlLines = Get-Content $gatewayYmlPath

    if ($appYmlLines -join "`n" -match "id:\s*$moduleName") {
        Write-Host "`n[INFO] ----> Route for '$moduleName' already exists in Gateway. Skipping insertion."
        return
    }

    $insertIndex = ($appYmlLines | Select-String -Pattern "routes:" -SimpleMatch).LineNumber

    if ($insertIndex -gt 0) {
        $before = $appYmlLines[0..$insertIndex]
        $after = $appYmlLines[($insertIndex + 1)..($appYmlLines.Length - 1)]
        $newYml = $before + $routeBlock + $after
        $newYml | Set-Content -Path $gatewayYmlPath
        Write-Host "`n`[SUCCESS] ==== Added route for '$moduleName' to Gateway"
    } else {
        Write-Host "`n`[ERROR] ----> 'routes:' section not found in Gateway application.yml. Add manually:"
        Write-Host $routeBlock
    }
} else {
    Write-Host "`n`[ERROR] ----> Gateway application.yml not found at expected path: $gatewayYmlPath"
}


if (Test-Path $settingsGradlePath) {
    $content = Get-Content $settingsGradlePath
    if ($content -notcontains $moduleEntry) {
        Add-Content -Path $settingsGradlePath -Value $moduleEntry
        Write-Host "`n`[SUCCESS] ==== Added '$moduleName' to settings.gradle"
    } else {
        Write-Host "`n`[ERROR] ----> '$moduleName' already included in settings.gradle"
    }
} else {
    Write-Host "`n`[ERROR] ----> settings.gradle not found in root. Please create it manually and add: $moduleEntry"
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
    name: $moduleName
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
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
}
"@ | Set-Content -Path $gradleFile



# Create empty .gitignore
New-Item -ItemType File -Path "$basePath\.gitignore" -Force | Out-Null

Write-Host "`nProject structure for '$moduleName' created."
Write-Host "`n`[SUCCESS] ==== Package: com.raksa.app"
Write-Host "`n`[SUCCESS] ==== Application class: $applicationClass.java"
Write-Host "`n`[SUCCESS] ==== application.yml and build.gradle configured.`n` "
