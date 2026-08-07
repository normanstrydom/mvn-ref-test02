# mvn-ref-test02

This repository is a Maven test project that demonstrates a GitHub Actions release workflow and dependency consumption from GitHub Packages.

## Overview

- Builds a simple Java application.
- Imports the BOM published by the sibling project `mvn-ref-test01` through Maven `dependencyManagement`.
- Uses a GitHub Actions workflow for manual releases from the `release` branch.

## Prerequisites

- Java 17
- Maven 3.9+
- A GitHub token with access to GitHub Packages and repository contents

## Consuming the BOM from GitHub Packages

`mvn-ref-test02` imports the BOM artifact `com.devtest:mvn-ref-test01-bom` from GitHub Packages.

The Maven repository URL should use the GitHub Packages owner endpoint:

```xml
<properties>
  <github.packages.url>https://maven.pkg.github.com/<OWNER></github.packages.url>
</properties>
```

### Maven authentication

Configure credentials in `~/.m2/settings.xml` or use the repository-local settings file at `.mvn/settings.xml`:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN_OR_PAT</password>
    </server>
  </servers>
</settings>
```

The token must include `read:packages` access.

## Local build

```powershell
$env:GITHUB_ACTOR="YOUR_GITHUB_USERNAME"
$env:GITHUB_TOKEN="YOUR_GITHUB_TOKEN_OR_PAT"
mvn -s .mvn/settings.xml -DskipTests package
```

## Release workflow

The GitHub Actions workflow in `.github/workflows/release.yml` is triggered manually and only runs on the `release` branch.

It expects these inputs:

- `releaseVersion`
- `developmentVersion`

The workflow uses:

- `actions/checkout`
- `actions/setup-java`
- Maven `release:prepare` and `release:perform`
- repository and package permissions via `GITHUB_TOKEN`

## Notes

- If Maven reports `401 Unauthorized` for GitHub Packages, verify that the token is valid and that it has package-read permissions.
- If the BOM version does not exist yet, update the imported version in `pom.xml` to match the published artifact version.

