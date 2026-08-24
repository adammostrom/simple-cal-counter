FROM docker.io/eclipse-temurin:21-jre

WORKDIR /simpleNutrition

COPY nutritionapp1.0.jar nutrition.jar

USER 1000

ENTRYPOINT ["java", "-jar", "nutrition.jar"]
