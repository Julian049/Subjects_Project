# Proyecto manejo de asignaturas  

## Introducción    
El proyecto consta del manejo de asignaturas, que permite manejar distintas consultas para recuperar información de las asignaturas que cumplan con alguna condición.

### Cómo usar la aplicación

Para poder usar cada una de las características de la aplicación, hay que tener en cuenta unas consideraciones antes.

### Al ejecutar el código nativo (Desde el IDE)

#### Libreria lista
La librería se encuentra en la carpeta **lib** que se encuentra en la raíz del proyecto, por lo que no es necesario hacer modificaciones de ruta de la misma librería, el programa es capaz de reconocerla automáticamente.

### Al ejecutar el jar del proyecto 

#### Libreria lista
La librería ya viene previamente instalada en el proyecto, por lo que no hay que hacer ajustes en la librería de la lista para que el jar del proyecto funcione sin ningún problema.

#### Ejecución del proyecto
Para ejecutar el proyecto usaremos alguna terminal, para el resto de la guía usaremos CMD, sin embargo, con alguna alternativa de terminal funcionaría igual por ejemplo PowerShell, luego de descargar el archivo `subjectsProject.jar` y suponiendo que el archivo se encuentra en la carpeta de descargas abrimos un CMD y ejecutamos el siguiente comando para acceder a la carpeta desde el CMD

`cd C:\Users\Julian\Downloads`

Sustituimos `C:\Users\Julian\Downloads` por la ruta en la cual esté el archivo **jar** del proyecto

Una vez dentro de la carpeta en el CMD, tendremos que ejecutar el siguiente comando para que el programa funcione correctamente

`java -jar subjectsProject.jar`

Con esto, el programa ya estaría ejecutándose correctamente y ya está listo para hacer diferentes consultas

### Métodos que incluye la aplicación

La aplicación tiene una gran cantidad de métodos en los cuales explicaré su uso y explicación a continuación

NOTA: Para los siguientes métodos usaremos el software Postman para poder ejecutar cada método correctamente

#### Asignaturas

Para el manejo de las asignaturas, la aplicación consta con 4 métodos: añadir, obtener, eliminar y modificar

##### Añadir
Para añadir una asignatura al programa, es necesario realizar la solicitud bajo un post y se tiene que acceder al siguiente enlace:

`http://localhost:8080/subject`

Y desde el body de Postman poner la información siguiendo la siguiente estructura.

>{
        "name": " ",
        "subjectCode": " "
    }

En donde, `name` será el nombre de la asignatura, por ejemplo, `quimica` y `subjectCode` el código de dicha asignatura, por ejemplo, `1234`. 

Al ejecutar esa línea, saldrá un mensaje, indicando que se añadió correctamente.

##### Listar
Para obtener una lista de las materias que están cargadas en el sistema, es necesario realizar la solicitud bajo un get y hay que acceder al siguiente enlace:

`http://localhost:8080/subject`

Con esto podrá obtener todas las asignaturas en formato JSON.

##### Eliminar
Para eliminar una asignatura existente, tendrá que hacer la solicitud bajo un get y acceder a la siguiente dirección.

`http://localhost:8080/subject/delete/code`

En donde `code` será el código de la asignatura que quiera eliminar. Al realizar la solicitud, saldrá un mensaje indicando que la eliminación de la materia fue exitosa.

##### Modificar 
Para poder modificar alguna materia, tendrá que acceder bajo un post e ingresar al siguiente enlace: 

`http://localhost:8080/subject/modify/code`

En donde `code` será el código de la materia que se modificará, además en el body tendrá que pasar la siguiente estructura:

>{
        "name": " ",
        "subjectCode": " "
}

Donde tendrá que añadir el nuevo nombre y código de la materia, es importante añadir los 2 parámetros debido a que se modificarán los 2 valores.

Seguido de esto, se mostrará un mensaje que indicará que la modificación fue exitosa.

#### Lugar

Para los métodos de lugar se sigue una estructura similar a la asignatura, por lo que no ahondaré mucho en los pasos. 

##### Añadir

Bajo un post se tendra que acceder al siguiente enlace:

`http://localhost:8080/place`

Ademas se tendra que poner un body con la informacion a añadir, siguiendo la siguiente estructura:

>{
        "name": " ",
        "placeCode": " ",
        "location": " "
}

En donde, `name` será el nombre del lugar,  por ejemplo, `Salon de matematicas`, `placeCode` será el código de dicho lugar, por ejemplo, `1234` y `location` será la ubicación del lugar, por ejemplo, `Edificio 1`

##### Listar
Para obtener una lista de los lugares, tendrá que acceder al siguiente enlace bajo un get:

`http://localhost:8080/place`

##### Eliminar
Para eliminar un lugar del sistema, podrá hacerlo bajo un get usando el siguiente enlace:

`http://localhost:8080/place/delete/code`

En donde `code` sera el codigo del lugar que desea eliminar

##### Modificar
Para modificar un lugar, podrá hacerlo bajo un post y usando el siguiente enlace:

`http://localhost:8080/place/modify/code`

En donde `code` será el código del lugar el cual querrá modificar; además, tendrá que poner un body para reescribir la información, siguiendo la siguiente estructura:

>{
        "name": " ",
        "placeCode": " ",
        "location": " "
}

#### Grupo
Igualmente, al ser los mismos métodos que asignatura y lugar, no me extendere mucho al explicar cómo ejecutarlos.

##### Añadir

Bajo un post se tendra que acceder al siguiente enlace:

`http://localhost:8080/group`

Ademas se tendra que poner un body con la informacion a añadir, siguiendo la siguiente estructura:

>{
        "subjectCode": " ",
        "placeCode": " ",
        "schedule": [
            " ",
            " ",
            " "
        ]
    }

En donde, `subjectCode` será el codigo de la materia,  por ejemplo, `1234`, `placeCode` será el código del lugar, por ejemplo, `1234` y `schedule` será el horario que va a tener ese grupo, por ejemplo, `Lunes 8:00-10:00`

NOTA: `schedule` al ser el horario que puede manejar el programa, tendrá un máximo de 3 horarios, por lo que si intenta añadir más horarios, el programa se lo impedirá. Por otro lado, si desea solo añadir 1 o 2 horarios, lo puede hacer, pero el resto tiene que dejar las comillas vacías.

IMPORTANTE: Es de suprema importancia que tanto `subjectCode` y `placeCode` sean códigos de materias que ya existan dentro del sistema para poder obtener la información correctamente

##### Listar
En este caso, el programa cuenta con 2 métodos que permiten obtener la información de formas diferentes.

Para obtener una lista de los grupos completa, es decir, con toda la información que tenga el grupo como nombre de la asignatura, lugar y grupo, como más información, se vería de la siguiente forma:

>{
        "subject": {
            "name": "Calculo III",
            "subjectCode": "8108213"
        },
        "place": {
            "name": "Salon 1",
            "placeCode": "1111",
            "location": "Edificio 1"
        },
        "schedule": [
            "Lunes 8:00-10:00",
            "Miercoles 8:00-10:00",
            "Viernes 8:00-10:00"
        ]
    }

Para obtener la información de esa forma, se tendrá que ejecutar desde el siguiente enlace:

`http://localhost:8080/group`

Por otro lado, si desea obtener la información de una forma más resumida con solo los códigos y el horario, de esta forma:

>{
        "subjectCode": "8108213",
        "placeCode": "1111",
        "schedule": [
            "Lunes 8:00-10:00",
            "Miercoles 8:00-10:00",
            "Viernes 8:00-10:00"
        ]
    }

Se tendrá que ejecutar el siguiente enlace:

`http://localhost:8080/group/allGroups` </br>

##### Eliminar
Para eliminar un grupo del sistema, podrá hacerlo bajo un get usando el siguiente enlace:

`http://localhost:8080/group/delete`

En este caso, para pasarle el grupo que desea eliminar, se tiene que hacer a través de body, es decir, se le pasa todo el objeto JSON, de la siguiente manera:

>{
        "subjectCode": "8108214",
        "placeCode": "1111",
        "schedule": [
            "Lunes 8:00-10:00",
            "Miercoles 8:00-10:00",
            "Viernes 8:00-10:00"
        ]
    }

Con esto, el programa compara el JSON ingresado para encontrar el objeto dentro del sistema.

##### Modificar
Para modificar un grupo, podrá hacerlo bajo un post y usando el siguiente enlace:

`http://localhost:8080/group/modify/subjectCode/placeCode`

En donde, `subjectCode` será el código de la asignatura y, `placeCode` el código del lugar del grupo, esto debido a que el código necesita ambos parámetros para encontrar el grupo que querrá modificar; además, tendrá que poner un body para reescribir la información, siguiendo la siguiente estructura:

> {
        "subjectCode": "mod",
        "placeCode": "mod",
        "schedule": [
            "mod 8:00-10:00",
            "mod 8:00-10:00",
            "mod 8:00-10:00"
        ]
    }

Esto modificará toda la informacion del grupo.

### Consultas

Pasando a métodos más generales, tenemos los siguientes:

#### Asignaturas que tienen asignado un mismo lugar

El método genera un listado de las asignaturas que están en el mismo lugar. Bajo un get se tiene que ejecutar el siguiente enlace:

`http://localhost:8080/group/getSubjectsByPlace/code`
``
Donde, `code` es el código del lugar al cual quiere obtener las asignaturas que tiene enlazadas.

#### Asignaturas con más de un grupo
Como su nombre lo indica, el método obtendrá todas las asignaturas que tengan más de un grupo. Para poder obteneralas bajo un get tenemos que usar el siguiente enlace:

`http://localhost:8080/group/SubjectsWithMoreGroup`

#### Asignaturas que tiene asignado el mismo horario
Como su nombre lo indica, este método obtiene todas las asignaturas que tengan al menos 1 horario igual, es decir, que si, por ejemplo, se le pasa `Lunes 8:00-10:00` va a obtener todas las materias que tengan ese horario. Bajo un get usamos el siguiente enlace:

`http://localhost:8080/group/getSubjectsBySchedule/schedule`

En donde `schedule` es el horario a buscar, por ejemplo, `Lunes 8:00-10:00`
