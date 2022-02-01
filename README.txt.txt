#Alkemy Challenge - Crear un API de los personajes de Disney.

Nombre: Hern�n Olmos
Lenguaje: Java

Herramientas utilizadas: Spring-boot, Spring Security, Spring JPA, Spring Hibernate, Spring mail, MySql, Swagger

#Como usar la Aplicaci�n:

-EJECUCI�N DE LA APLICACI�N

1. Clonar el repositorio en una carpeta.

2.  
  a. Abrirlo con un IDE y ejecutarlo como una aplicaci�n Spring. 
  b. Abrir el directorio del proyecto desde el cmd e ingresar "mvnw spring-boot:run"


*El programa utiliza un gestor de base de datos sql, por lo que ser� necesario conectarlo o cambiar la fuente de base de datos dentro src>main>resources>application.properties.

3. Una vez inicializado el programa evaluar� si existe una base de datos con el nombre "disneky_api_alkemychallenge".

4. En caso de querer utilizar la interfaz de Swagger para probar el API ingresar en "localhost:8081/swagger-ui/index.html". En caso de que el puerto est� ocupado, cambiarlo dentro de src>main>resources>application.properties>server.port.

-CREACI�N DE USUARIO

Para acceder a los endpoints de la aplicaci�n es necesario autenticarse.
Para esto se debe:

1. Ingresar dentro de AUTHENTICATION.
2. Ingresar dentro del m�todo POST register y clickear en Try it out.
3. Ingresar un email personal y una constrase�a.
	-En caso de que la data ingresada sea valida se creara un nuevo usuario en la base de datos con los datos ingresados (el password estar� encriptado por un password encoder). 
Luego recibir� un email de bienvenida a la casilla del correo ingresado, enviado desde Gmail utilizando la librer�a Spring Mail.
4. Dirigirseal m�todo POST login y volver a ingresar los datos previamente entregados al servidor.
5. Si el usuario est� registrado en la base de datos recibir� un token JWT dentro de la respuesta de m�todo.
6. Copiar el token sin las comillas y dentro de la casilla "Authorize" arriba a la derecha, ingresar el texto "Bearer" seguido de un espacio y el token.

La seguridad del servidor (Spring Security) evaluar� este token cada vez que se haga un pedido a la base de datos para confirmar la identidad del usuario. 
En caso de ingresar un token incorrecto los m�todos deber�n devolver un error 401 (usuario no autorizado).

-USO DEL API
Dentro del API encontrar� tres controladores, cada uno con cuatro m�todos que se corresponden con las operaciones CRUD (create, read, update and delete).

1. Character Controller
Este controlador est� encargado de gestionar los datos que tengan que ver con los personajes.

M�todo POST: Ingresar los datos que ser�n guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo "Character" y guardarlo en la base de datos. 
--Campo "name": Obligatorio. En este campo se ingresa el nombre de personaje. Dicho campo est� configurado como una columna �nica dentro del dise�o de la base de datos. Eso quiere decir que no podr�n guardarse dos personajes con el mismo nombre.
--Campo "movies": Opcional. En el caso de este campo registrar� la base de datos para evaluar si las pel�culas ingresadas existen, en caso contrario crear� las respectivas instancias en la base de datos, las cuales tendr�n todos sus campos nulos, salvo el nombre.
--Campo "imageUrl": Opcional. URL de la imagen del personaje que ser� consumida luego por el cliente.
--Campo "background" :Opcional. Texto sobre la historia del personaje.
--Campo "age": Opcional. Edad del personaje.
--Campo "weight": Opcional. Peso del personaje.

M�todo GET: Se devuelve un JSON con los datos de los personajes guardados en la base de datos.
A este m�todo se pueden pasar parametros para filtrar tales busquedas por nombre, edad o pel�culas en las que aparecen. No es posible ingresar mas de un cr�terio de busqueda a la vez.  En caso de no especificarse ninguno se devolver�n todos los personajes que se encuentren en la base de datos.

Para devolver los resultados utilizo otro DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y pel�culas).

M�todo PUT: Se actualiza un personaje en part�cular con informaci�n nueva. Este m�todo es muy similar al POST. Todos los campos son opcionales salvo el nombre del personaje modificado y el ID del personaje que se quiera modificar. 
Atenci�n: En caso de no completar ciertos campos se actualizar�n como nulos, pudiendo perder as� informaci�n sobre el personaje.

M�todo DELETE: Borra un personaje de la base de datos en base a su ID.

2. Movie Controller
Este controlador est� encargado de gestionar los datos que tengan que ver con las pel�culas.

M�todo POST: Ingresar los datos que ser�n guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo "Movie" y guardarlo en la base de datos. 
--Campo "name": Obligatorio. En este campo se ingresa el nombre de la pel�cula. Dicho campo est� configurado como una columna �nica dentro del dise�o de la base de datos. Eso quiere decir que no podr�n guardarse dos pel�culas con el mismo nombre.
--Campo "characters": Opcional. En el caso de este campo registrar� la base de datos para evaluar si los personajes ingresados existen, en caso contrario crear� las respectivas instancias en la base de datos, las cuales tendr�n todos sus campos nulos, salvo el nombre.
--Campo "genres": Opcional. En el caso de este campo registrar� la base de datos para evaluar si los g�neros ingresados existen, en caso contrario crear� las respectivas instancias en la base de datos, las cuales tendr�n todos sus campos nulos, salvo el nombre.
--Campo "imageUrl": Opcional. URL de la imagen de la pel�cula que ser� consumida luego por el cliente.
--Campo "rating" :Opcional. Evaluaci�n de la pel�cula en base a un cr�terio de 5 opciones.
--Campo "release date": Opcional. Fecha de estreno de la pel�cula.

M�todo GET: Se devuelve un JSON con los datos de las pel�culas guardadas en la base de datos.
A este m�todo se pueden pasar parametros para filtrar tales busquedas por nombre, g�nero o alterar el orden en el que aparecen en la lista. No es posible ingresar mas de un cr�terio de busqueda a la vez.  En caso de no especificarse ninguno se devolver�n todos las pel�culas que se encuentren en la base de datos.

Para devolver los resultados utilizo otro DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y pel�culas).

M�todo PUT: Se actualiza una pel�cula en part�cular con informaci�n nueva. Este m�todo es muy similar al POST. Todos los campos son opcionales salvo el nombre de la pel�cula modificada y el ID de la pel�cula que se quiera modificar. 
Atenci�n: En caso de no completar ciertos campos se actualizar�n como nulos, pudiendo perder as� informaci�n sobre la pel�cula.

M�todo DELETE: Borra una pel�cula de la base de datos en base a su ID.

3. Genre Controller
Este controlador est� encargado de gestionar los datos que tengan que ver con los g�neros.

M�todo POST: Ingresar los datos que ser�n guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo "Genre" y guardarlo en la base de datos. 
--Campo "name": Obligatorio. En este campo se ingresa el nombre del g�nero. Dicho campo est� configurado como una columna �nica dentro del dise�o de la base de datos. Eso quiere decir que no podr�n guardarse dos g�neros con el mismo nombre.
n la base de datos, las cuales tendr�n todos sus campos nulos, salvo el nombre.
--Campo "imageUrl": Opcional. URL de la imagen representativa del g�nero que ser� consumida luego por el cliente.


M�todo GET: Se devuelve un JSON con los datos de los g�neros guardados en la base de datos.
A este m�todo se pueden pasar parametros para filtrar tales busquedas seg�n el ID del g�nero. En caso de no pasarse ning�n parametro, se devolver� una lista con todos los g�neros.

Para devolver los resultados utilizo otro DTO que devuelve el nombre, la imagen y las pel�culas de la base de datos que pertenezcan a dicho genero.

M�todo PUT: Se actualiza un g�nero en part�cular con informaci�n nueva. Este m�todo es muy similar al POST. Todos los campos son opcionales salvo el nombre del g�nero modificado y el ID del g�nero que se quiera modificar. 
Atenci�n: En caso de no completar ciertos campos se actualizar�n como nulos, pudiendo perder as� informaci�n sobre el g�nero.

M�todo DELETE: Borra un g�nero de la base de datos en base a su ID.
