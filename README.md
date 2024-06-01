# Minijuegos API

<div align="center">
  <img src="https://github.com/aleramiirez/MiniJuegos/assets/121113496/65feed69-1219-454c-bc27-61f1af682380" alt="Proyecto" width="500"/>
</div>

## Instalación

## Con Docker

1. Escribe en la terminal el comando `git clone https://github.com/anavarroo/GestionGenericaP.git`
2. Escribe en la terminal el comando `mvn clean package -DskipTest` para limpiar la aplicación.
3. Escibe `docker-compose up` para iniciar la aplicación..
4. Para entrar en la aplicación escribe lo siguiente en el navegador `http://localhost:3000/login`

## Sin Docker

1. Escribe en la terminal el comando `git clone https://github.com/anavarroo/GestionGenericaP.git`
2. Comprueba que tienes todas las versiones correctas de [Entorno](https://github.com/anavarroo/GestionGenericaP/wiki/Punto-de-vista-Tecnico#--requisitos-del-sistema)
3. Escribe en la terminal el comando `mvn clean verify -DskipTest` para entrar en cada carpeta y construit la aplicación.
4. Despues ejecuta `mvn spring-boot:run` para entrar en cada carpeta y iniciar la aplicación.
