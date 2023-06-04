# Trabajo Práctico de la materia Programación con objetos avanzada

## Crear proyecto:  
- Crear proyecto Maven  
    - Definir los parámetros:  
```  
groupId: com.tpPOA
artifactId: tpPOA
```

- Editar archivo pom.xml  
    - Definir la versión de Java:
```XML
<properties>
	<maven.compiler.source>19</maven.compiler.source>
	<maven.compiler.target>19</maven.compiler.target>
</properties>
```  
        - Agregar dependencias de SqLite y Apache POI:
```XML
<dependencies>
	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi</artifactId>
		<version>5.2.3</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi-ooxml</artifactId>
		<version>5.2.3</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
	<dependency>
		<groupId>org.xerial</groupId>
		<artifactId>sqlite-jdbc</artifactId>
		<version>3.41.2.1</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
	<dependency>
		<groupId>org.apache.logging.log4j</groupId>
		<artifactId>log4j-core</artifactId>
		<version>2.20.0</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
	<dependency>
		<groupId>org.apache.logging.log4j</groupId>
		<artifactId>log4j-api</artifactId>
		<version>2.20.0</version>
	</dependency>
</dependencies>
```

## Importar proyecto:
- Importar los archivos
- Editar el archivo pom.xml con la versión correspondiente de Java
- Actualizar el proyecto Maven

## Ejecutar aplicación:
- Compilar y ejecutar el archivo principal.class


