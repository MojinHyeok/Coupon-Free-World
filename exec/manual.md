## 배포시 주의사항

- Java Version 1.8

- 백엔드 서버 프로젝트 관리도구:Maven

- DB:mysql사용

- 프론트 서버 패키지 관리자: NPM

- sudo apt update && sudo apt -y upgrade

- sudo apt install mysql-server&&

- sudo apt install openjdk-8-jdk &&

- sudo apt install maven &&

- sudo apt install npm

  -----

## DB설정

sudo mysql

grant all privileges on *.* to 'root'@'%' identified by ‘1234';

create database subpjt2 

use subpjt2 -> 덤프파일 실행

------------

## 백엔드 동작

동작 테스트:mvn spring-boot:run

빌드:mvn package

------

## 프론트엔드 동작

동작 테스트

npm install

npm run serve

빌드: npm run build