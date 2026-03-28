## Quick start - Microservices

### Workspace Structure

Create a workspace structure.  
Do NOT put everything in one Spring project.  

    microservices/
    ├── product-service
    ├── review-service
    ├── recommendation-service
    └── product-composite-service

### Create the Core Microservices (CLI)

Product service:

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.product \
    --groupId=com.minte9.microservices.core.product \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=product-service

Review service:

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.review \
    --groupId=com.minte9.microservices.core.review \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=review-service

Recommendation service:

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.recommendation \
    --groupId=com.minte9.microservices.core.recommendation \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=recommendation-service

Health Check (simultaneous):

    curl http://localhost:7001/actuator/health  {"status":"UP"}
    curl http://localhost:7002/actuator/health  {"status":"UP"}
    curl http://localhost:7003/actuator/health  {"status":"UP"}

### Create the Composite Service

Product Composite service:

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.composite.product \
    --groupId=com.minte9.microservices.core.composite.product \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=product-composite-service

Ports recap:

- product: 7001
- review: 7002
- recommendation: 7003
- composite: 7000

Start the services:

    ./gradlew bootRun 

Start all services:

    sh sh/start-all.sh

### Test the services 

    curl http://localhost:7001/product/1
    curl http://localhost:7002/review?productId=1
    curl http://localhost:7003/recommendation?productId=1

### Test the Composite Service

    curl http://localhost:7000/product-composite/1

    {"productId":1,"name":"Product-1","weight":100,
     "reviews":[
        {"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},
        {"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}],
     "recommendations":[
        {"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},
        {"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}
    ]}