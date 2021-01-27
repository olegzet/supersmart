# Validation API Dev Guide
All building and deploying process are minimized. To launch application you should clone from Git sources to local storage 
and run Application.

After start application you can see fully described REST API of the Application by URL:
http://localhost:8080/api/v1/swagger-ui.html
with real payload.

## Building and running
Project uses Gradle as a build tool with Gradle Wrapper.
Building is as easy as running the following shell command on Linux / Unix systems
(assuming current directory is the project checkout folder):

    ./gradlew build

running the App:

    ./gradlew bootRun

## Testing
Testing is running by flowing shell command (assuming current directory is the project checkout folder):

    ./gradlew test

## Additional Information
I hope you will enjoy using my application :)