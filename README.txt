#Alkemy Challenge 
##  _Crear un API de los personajes de Disney._

**Nombre:** Hernán Olmos
**Lenguaje:** Java
**Herramientas utilizadas:**
- Spring-boot
- Spring Security 
- Spring JPA 
- Spring Hibernate 
- Spring Mail 
- MySql 
- Swagger

## Como usar la Aplicación:

### EJECUCIÓN DE LA APLICACIÓN

1. Clonar el repositorio en una carpeta.

```sh
abrir gitbash
ingresar el comando git clone https://github.com/her-o/Disney-API.git
```

2.  Iniciar el proyecto.*
  a. Abrirlo con un IDE y ejecutarlo como una aplicación Spring. 
  b. Abrir desde la consola:
_Dentro de la carpeta del proyecto ingresar el siguiente comando:_

```sh
mvn spring-boot:run
```

_*El programa utiliza un gestor de base de datos sql, por lo que será necesario conectarlo o cambiar la fuente de base de datos dentro src>main>resources>application.properties._

3. Una vez inicializado el programa evaluará si existe una base de datos con el nombre "disney_api_alkemychallenge".

4. Uso de la interfaz grafica de Swagger.
_Ingresar en la siguiente URL:_
```sh
localhost:8082/swagger-ui/index.html
```
_Si puerto esté ocupado, cambiarlo dentro de src>main>resources>application.properties>server.port_

### CREACIÓN DE USUARIO

Para acceder a los endpoints de la aplicación es necesario autenticarse.
Dentro de la interfaz de Swagger, seguir los siguentes pasos:

1. Ingresar dentro de AUTHENTICATION.
2. Ingresar dentro del método POST register y clickear en Try it out.
3. Ingresar un email personal y una constraseña.
	-En caso de que la data ingresada sea valida se creara un nuevo usuario en la base de datos con los datos ingresados (el password estará encriptado por un password encoder). 
Luego recibirá un email de bienvenida a la casilla del correo ingresado, enviado desde Gmail utilizando la librería Spring Mail.
4. Dirigirseal método POST login y volver a ingresar los datos previamente entregados al servidor.
5. Si el usuario está registrado en la base de datos recibirá un token JWT dentro de la respuesta de método.
6. Copiar el token sin las comillas y dentro de la casilla "Authorize" arriba a la derecha, ingresar el texto "Bearer" seguido de un espacio y el token.

_La seguridad del servidor (Spring Security) evaluará este token cada vez que se haga un pedido a la base de datos para confirmar la identidad del usuario. 
En caso de ingresar un token incorrecto los métodos deberán devolver un error 401 (usuario no autorizado)._

### USO DEL API
Dentro del API encontrará tres controladores, cada uno con cuatro métodos que se corresponden con las operaciones CRUD (create, read, update and delete).

##### 1. Character Controller
_Este controlador está encargado de gestionar los datos que tengan que ver con los personajes._

- **Método POST** 
  Ingresar los datos que serán guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo "Character" y guardarlo en la base de datos. 

     _name_: Obligatorio.
            En este campo se ingresa el nombre de personaje. Dicho campo está configurado como una columna única dentro del diseño de la base de datos. Eso quiere decir que no podrán guardarse dos personajes con el mismo nombre.
   
     _movies_: Opcional. 
        En el caso de este campo registrará la base de datos para evaluar si las películas ingresadas existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.
        
    _imageUrl_: Opcional. 
        URL de la imagen del personaje que será consumida luego por el cliente.

    _background_ : Opcional. 
        Texto sobre la historia del personaje.

    _age_: Opcional. 
        Edad del personaje.

    _weight_: Opcional. 
        Peso del personaje.

- **Método GET**
     Se devuelve un JSON con los datos de los personajes guardados en la base de datos.
A este método se pueden pasar parametros para filtrar tales busquedas por nombre, edad o películas en las que aparecen. 
**No es posible ingresar mas de un críterio de busqueda a la vez.**  _En caso de no especificarse ninguno se devolverán todos los personajes que se encuentren en la base de datos._

    Para devolver los resultados se utiliza un DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y películas).

- **Método PUT**
    Se actualiza un personaje en partícular con información nueva. 
    Este método es muy similar al POST. 
    Todos los campos son opcionales salvo el nombre del personaje modificado y el ID del personaje que se quiera modificar. 
    **Atención:** En caso de no completar ciertos campos se actualizarán como nulos, pudiendo perder así información sobre el personaje.

- **Método DELETE** 
    Borra un personaje de la base de datos en base a su ID.

##### 2. Movie Controller
_Este controlador está encargado de gestionar los datos que tengan que ver con las películas._

**Método POST** 
Ingresar los datos que serán guardados en un DTO (Data Transfer Object) para luego convertirlos en un objeto de tipo "Movie" y guardarlo en la base de datos.
    
    _name_: Obligatorio.
    En este campo se ingresa el nombre de la película. 
    Dicho campo está configurado como una columna única dentro del diseño de la base de datos. Eso quiere decir que no podrán guardarse dos películas con el mismo nombre.
    
    _characters_: Opcional. 
    En el caso de este campo registrará la base de datos para evaluar si los personajes ingresados existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.
    
    _genres_: Opcional. 
    En el caso de este campo registrará la base de datos para evaluar si los géneros ingresados existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.
    
    _imageUrl_: Opcional. 
    URL de la imagen de la película que será consumida luego por el cliente.
    
    _rating_ :Opcional. 
    Evaluación de la película en base a un críterio de 5 opciones.
    
    __release date__: Opcional. 
    Fecha de estreno de la película.

**Método GET** 
Se devuelve un JSON con los datos de las películas guardadas en la base de datos.
A este método se pueden pasar parametros para filtrar tales busquedas por nombre, género o alterar el orden en el que aparecen en la lista. 
**No es posible ingresar mas de un críterio de busqueda a la vez.**  En caso de no especificarse ninguno se devolverán todos las películas que se encuentren en la base de datos.

Para devolver los resultados utilizo otro DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y películas).

**Método PUT** 
Se actualiza una película en partícular con información nueva. Este método es muy similar al POST. 
Todos los campos son opcionales salvo el nombre de la película modificada y el ID de la película que se quiera modificar. 
**Atención:** _En caso de no completar ciertos campos se actualizarán como nulos, pudiendo perder así información sobre la película._

**Método DELETE:** Borra una película de la base de datos en base a su ID.

##### 3. Genre Controller
Este controlador está encargado de gestionar los datos que tengan que ver con los géneros.

**Método POST** 
Ingresar los datos que serán guardados en un DTO (Data Transfer Object) para luego convertirlos en un objeto de tipo "Genre" y guardarlo en la base de datos. 

_name_: Obligatorio. 
En este campo se ingresa el nombre del género. 
Dicho campo está configurado como una columna única dentro del diseño de la base de datos. Eso quiere decir que no podrán guardarse dos géneros con el mismo nombre.
n la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.

_imageUrl_: Opcional. 
URL de la imagen representativa del género que será consumida luego por el cliente.


**Método GET** 
Se devuelve un JSON con los datos de los géneros guardados en la base de datos.
A este método se pueden pasar parametros para filtrar tales busquedas según el ID del género. En caso de no pasarse ningún parametro, se devolverá una lista con todos los géneros.

Para devolver los resultados utilizo otro DTO que devuelve el nombre, la imagen y las películas de la base de datos que pertenezcan a dicho genero.

**Método PUT** 
Se actualiza un género en partícular con información nueva. 
Este método es muy similar al POST. Todos los campos son opcionales salvo el nombre del género modificado y el ID del género que se quiera modificar. 
**Atención:** _En caso de no completar ciertos campos se actualizarán como nulos, pudiendo perder así información sobre el género._

**Método DELETE** 
Borra un género de la base de datos en base a su ID.



