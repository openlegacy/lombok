# OpenLegacy Lombok

## Steps to compile

* `ant clean` - cleans dist directory
* `ant setupJavaOracle8TestEnvironment` - set up test environment
* `ant test-ol-lombok`
* `mvn clean source:jar install` - copies jar to the local maven repository
* `mvn -P develop clean deploy` - copies jar to the remote OpenLegacy Maven Repository

