# Step 1: Build Stage
FROM gradle:8-jdk17 AS builder
WORKDIR /app

# Gradle 캐시 활용을 위한 설정
COPY gradle /app/gradle
COPY build.gradle settings.gradle gradlew /app/
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

# 전체 프로젝트 복사 후 빌드
COPY . .
RUN ./gradlew clean build --no-daemon

# Step 2: Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 JAR 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
