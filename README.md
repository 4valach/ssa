Para instalar la aplicacion usar el wrapper de maven


.mvnw clean install


Para ejecutar la aplicacion tenemos dos perfiles, El perfil CON errores se seguridad 
cd jar
../.mvnw spring-boot:run -PbadCode

y el perfil SIN errores de seguridad
cd jar
../.mvnw spring-boot:run -PbadCode

conectarse en localhost:8080 y leer el texto de la página localhost:8080/instruction.html para más información