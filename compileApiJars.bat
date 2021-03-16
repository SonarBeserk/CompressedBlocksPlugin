@echo off
IF NOT EXIST BuildTools (
    mkdir BuildTools
)
cd BuildTools
curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar

echo "Running v1.13.2 build"
java -jar BuildTools.jar --rev 1.13.2

echo "Installing v1.13.2 locally"
cmd /c mvn -B install:install-file -Dfile=spigot-1.13.2.jar -DgroupId=org.spigotmc -DartifactId=spigot -Dversion=1.13.2-R0.1-SNAPSHOT -Dpackaging=jar

pause

echo "Running v1.16.3 build"
java -jar BuildTools.jar --rev 1.16.3

echo "Installing v1.16.3 locally"
cmd /c mvn -B install:install-file -Dfile=spigot-1.16.3.jar -DgroupId=org.spigotmc -DartifactId=spigot -Dversion=1.16.3-R0.1-SNAPSHOT -Dpackaging=jar

pause

echo "Running v1.16.5 build"
java -jar BuildTools.jar --rev 1.16.5

echo "Installing v1.16.5 locally"
cmd /c mvn -B install:install-file -Dfile=spigot-1.16.5.jar -DgroupId=org.spigotmc -DartifactId=spigot -Dversion=1.16.5-R0.1-SNAPSHOT -Dpackaging=jar

pause