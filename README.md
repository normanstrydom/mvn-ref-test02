# mvn-ref-test02

github workflow / release test 

Refer mvn-ref-test01 for details

## Consuming `mvn-ref-test01` Final releases from GitHub Packages

`mvn-ref-test02` is configured to import BOM version `1.0.0-Final`.

Before build:

1. Update `github.packages.url` in `pom.xml` to your repository URL:
	 - `https://maven.pkg.github.com/<OWNER>/<REPO>`
2. Configure Maven credentials in `~/.m2/settings.xml`:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
					xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
	<servers>
		<server>
			<id>github</id>
			<username>GITHUB_USERNAME</username>
			<password>GITHUB_TOKEN_OR_PAT</password>
		</server>
	</servers>
</settings>
```

3. Build:

```powershell
mvn -f F:\git-work\mvn-ref-test02\pom.xml clean package
```