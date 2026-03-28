## MongoDB - Microservice Persistence

Add MongoDB to docker-compose (Spring Data environment).

    services:

    mongodb:
        image: mongo:7.0
        container_name: mongodb
        ports:
            - "27017:27017"
        environment:
            MONGO_INITDB_DATABASE: product-db
        volumes:
            - mongodb-data:/data/db

    product-service:
        image: product-service:1.0
        container_name: product-service
        build: ../spring_data/product-service
        ports:
            - "7001:7001"
        depends_on:
            - mongodb
        environment:
            SPRING_DATA_MONGODB_HOST: mongodb
            SPRING_DATA_MONGODB_PORT: 27017

    review-service:
        image: review-service:1.0
        container_name: review-service
        build: ../spring_data/review-service
        ports:
            - "7002:7002"
        depends_on:
            - mongodb
        environment:
            SPRING_DATA_MONGODB_HOST: mongodb
            SPRING_DATA_MONGODB_PORT: 27017

    recommendation-service:
        image: recommendation-service:1.0
        container_name: recommendation-service
        build: ../spring_data/recommendation-service
        ports:
            - "7003:7003"
        depends_on:
            - mongodb
        environment:
            SPRING_DATA_MONGODB_HOST: mongodb
            SPRING_DATA_MONGODB_PORT: 27017

    product-composite-service:
        image: product-composite-service:1.0
        container_name: product-composite-service
        build: ../spring_data/product-composite-service
        ports:
            - "7000:7000"
        depends_on:
            - product-service
            - review-service
            - recommendation-service

    volumes:
        mongodb-data:
    