# Docker 镜像构建
# @author <a href="https://gitee.com/deng-2022">回忆如初</a>
# @from <a href="https://deng-2022.gitee.io/blog/">Memory's Blog</a>

FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY memory-api/pom.xml .
COPY memory-api/src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/springboot-init-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]