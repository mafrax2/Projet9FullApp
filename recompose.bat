@echo off
cd mediscreen
call ./gradlew clean build -x test
cd ..
cd mediscreen-notes
call ./gradlew clean build -x test
cd ..
cd mediscreen-client
call ./gradlew clean build
cd ..
call docker-compose up --build
