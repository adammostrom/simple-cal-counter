FROM docker.io/eclipse-temurin:21-jre

WORKDIR /simpleNutrition

COPY nutritionPackage.jar nutrition.jar

USER 1000

ENTRYPOINT ["java", "-jar", "nutrition.jar"]
