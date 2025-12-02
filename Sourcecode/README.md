# Project Setup and Configuration

This README describes the steps required to set up, configure, and deploy the application.

## 1. Database Initialization (Bootstrap)

Before deploying the application to Tomcat8.5.x, you **must** initialize the database using the provided bootstrap script. This process creates the necessary database schema and populates it with initial data.

### How to Run
1. Open a terminal.
2. Navigate to the project root directory.
3. Run the bootstrap script:
   ```bash
   ./run_bootstrap.sh
   ```

### What the Script Does
- Creates a `logs` directory for output logs.
- Compiles all Java source files in `src`.
- Executes the `com.indven.tools.bootstrap.BootstrapDataLoader` class.
- Uses configuration from `resources` and libraries from `WebContent/WEB-INF/lib`.
- **Note:** This step is essential. Do not attempt to deploy the web archive (WAR) or exploded folder to Tomcat until this process completes successfully.

## 2. Database Configuration

The database connection settings are defined in `WebContent/WEB-INF/classes/projecthibernate.properties`.

You can modify this file to specify a different database, user, or password:

```properties
hibernate.connection.url = jdbc:mysql://127.0.0.1:3306/mdr?autoReconnect=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true
hibernate.connection.username = root
hibernate.connection.password = <your-root-password>
```

## 3. File Path Configuration

Target media file paths must be configured to point to the appropriate locations on your system (Linux, Windows, or macOS). These settings are found in `WebContent/WEB-INF/classes/ApplicationResources.properties`.

Update the following variables to match your environment:

```properties
# Path to image files
images.system.path = /path/to/media/<mdr-application-instance-name>/img/

# Path to resized image files
imagesResize.system.path = /path/to/media/<mdr-application-instance-name>/resizeimg/

# Path to audio/attachment files
audio.system.path = /path/to/media/<mdr-application-instance-name>/attachmentFile

# Path to the PDFBox JAR file
pdfapp.jarfile.path = /path/to/pdfbox-app-2.0.3.jar
```

## 4. Frontend Configuration

The application uses a JavaScript file to handle user add/update features in the web menu. You must ensure the context path matches your deployed web application folder name.

File: `assets/js/searchformgenerator.js` (approx. line 153)

Find the variable `mdrFolder` and set it to your web application's context name (the folder name in `webapps`).

```javascript
// If your deployed webapp folder is called "SomethingElse", set this to "SomethingElse"
var mdrFolder = "mdr"; 
```

This setting controls the path construction for icons and actions in the search result interface.

## 5. Legacy Restictions

This Java Web Application is built on top of the legacy Indven MDR application. It runs only on Apache Tomcat 8.5.x and MySQL. This is due to Struts2 library dependencies.
