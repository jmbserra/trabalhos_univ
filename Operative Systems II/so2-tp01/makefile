all:
	mkdir build
	mkdir build/classes
	javac -classpath .:src -d build/classes src/so2/*.java
	cp src/so2/svlocal.properties build/classes/so2

Server:
	java -classpath .:build/classes:resources/postgresql-42.2.5.jar so2.Server localhost 9009

Client:
	java -classpath .:build/classes so2.Client localhost 9009


clean:
	rm -rf build/
