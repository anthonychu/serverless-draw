# Serverless Draw - Azure Functions with Azure SignalR Service (JavaScript, C#, Java)

![](SignalR-java.gif)

## Java

```bash
cd src/java
cp local.settings.sample.json local.setting.json
# Update local.setting.json with SignalR Service connection string
mvn clean package -DskipTests
mvn azure-functions:run
```

## JavaScript

```bash
cd src/javascript
cp local.settings.sample.json local.setting.json
# Update local.setting.json with SignalR Service connection string
func extensions install
func start
```

## C\#

```bash
cd src/csharp
cp local.settings.sample.json local.setting.json
# Update local.setting.json with SignalR Service connection string
func start
```
