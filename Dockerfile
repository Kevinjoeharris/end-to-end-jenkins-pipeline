FROM ubuntu:latest

WORKDIR /app

#Copy dependencies
COPY 

COPY . .

CMD ["java","-jar","target\spring-cicd-demo-1.0.0.jar"]