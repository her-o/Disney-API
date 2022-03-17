<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="Alkemy_Challenge_0"></a>Alkemy Challenge</h1>
<h2 class="code-line" data-line-start=1 data-line-end=2 ><a id="_Crear_un_API_de_los_personajes_de_Disney__1"></a><em>Crear un API de los personajes de Disney.</em></h2>
<p class="has-line-data" data-line-start="3" data-line-end="6"><strong>Nombre:</strong> Hernán Olmos<br>
<strong>Lenguaje:</strong> Java<br>
<strong>Herramientas utilizadas:</strong></p>
<ul>
<li class="has-line-data" data-line-start="6" data-line-end="7">Spring-boot</li>
<li class="has-line-data" data-line-start="7" data-line-end="8">Spring Security</li>
<li class="has-line-data" data-line-start="8" data-line-end="9">Spring JPA</li>
<li class="has-line-data" data-line-start="9" data-line-end="10">Spring Hibernate</li>
<li class="has-line-data" data-line-start="10" data-line-end="11">Spring Mail</li>
<li class="has-line-data" data-line-start="11" data-line-end="12">MySql</li>
<li class="has-line-data" data-line-start="12" data-line-end="14">Swagger</li>
</ul>
<h2 class="code-line" data-line-start=14 data-line-end=15 ><a id="Como_usar_la_Aplicacin_14"></a>Como usar la Aplicación:</h2>
<h3 class="code-line" data-line-start=16 data-line-end=17 ><a id="EJECUCIN_DE_LA_APLICACIN_16"></a>EJECUCIÓN DE LA APLICACIÓN</h3>
<ol>
<li class="has-line-data" data-line-start="18" data-line-end="20">Clonar el repositorio en una carpeta.</li>
</ol>
<pre><code class="has-line-data" data-line-start="21" data-line-end="24" class="language-sh">abrir gitbash
ingresar el comando git <span class="hljs-built_in">clone</span> https://github.com/her-o/Disney-API.git
</code></pre>
<ol start="2">
<li class="has-line-data" data-line-start="25" data-line-end="30">Iniciar el proyecto.*<br>
a. Abrirlo con un IDE y ejecutarlo como una aplicación Spring.<br>
b. Abrir desde la consola:<br>
<em>Dentro de la carpeta del proyecto ingresar el siguiente comando:</em></li>
</ol>
<pre><code class="has-line-data" data-line-start="31" data-line-end="33" class="language-sh">mvn spring-boot:run
</code></pre>
<p class="has-line-data" data-line-start="34" data-line-end="35"><em>*El programa utiliza un gestor de base de datos sql, por lo que será necesario conectarlo o cambiar la fuente de base de datos dentro src&gt;main&gt;resources&gt;application.properties.</em></p>
<ol start="3">
<li class="has-line-data" data-line-start="36" data-line-end="38">
<p class="has-line-data" data-line-start="36" data-line-end="37">Una vez inicializado el programa evaluará si existe una base de datos con el nombre “disney_api_alkemychallenge”.</p>
</li>
<li class="has-line-data" data-line-start="38" data-line-end="40">
<p class="has-line-data" data-line-start="38" data-line-end="40">Uso de la interfaz grafica de Swagger.<br>
<em>Ingresar en la siguiente URL:</em></p>
</li>
</ol>
<pre><code class="has-line-data" data-line-start="41" data-line-end="43" class="language-sh">localhost:<span class="hljs-number">8082</span>/swagger-ui/index.html
</code></pre>
<p class="has-line-data" data-line-start="43" data-line-end="44"><em>Si puerto esté ocupado, cambiarlo dentro de src&gt;main&gt;resources&gt;application.properties&gt;server.port</em></p>
<h3 class="code-line" data-line-start=45 data-line-end=46 ><a id="CREACIN_DE_USUARIO_45"></a>CREACIÓN DE USUARIO</h3>
<p class="has-line-data" data-line-start="47" data-line-end="49">Para acceder a los endpoints de la aplicación es necesario autenticarse.<br>
Dentro de la interfaz de Swagger, seguir los siguentes pasos:</p>
<ol>
<li class="has-line-data" data-line-start="50" data-line-end="51">Ingresar dentro de AUTHENTICATION.</li>
<li class="has-line-data" data-line-start="51" data-line-end="52">Ingresar dentro del método POST register y clickear en Try it out.</li>
<li class="has-line-data" data-line-start="52" data-line-end="55">Ingresar un email personal y una constraseña.<br>
-En caso de que la data ingresada sea valida se creara un nuevo usuario en la base de datos con los datos ingresados (el password estará encriptado por un password encoder).<br>
Luego recibirá un email de bienvenida a la casilla del correo ingresado, enviado desde Gmail utilizando la librería Spring Mail.</li>
<li class="has-line-data" data-line-start="55" data-line-end="56">Dirigirseal método POST login y volver a ingresar los datos previamente entregados al servidor.</li>
<li class="has-line-data" data-line-start="56" data-line-end="57">Si el usuario está registrado en la base de datos recibirá un token JWT dentro de la respuesta de método.</li>
<li class="has-line-data" data-line-start="57" data-line-end="59">Copiar el token sin las comillas y dentro de la casilla “Authorize” arriba a la derecha, ingresar el texto “Bearer” seguido de un espacio y el token.</li>
</ol>
<p class="has-line-data" data-line-start="59" data-line-end="61"><em>La seguridad del servidor (Spring Security) evaluará este token cada vez que se haga un pedido a la base de datos para confirmar la identidad del usuario.<br>
En caso de ingresar un token incorrecto los métodos deberán devolver un error 401 (usuario no autorizado).</em></p>
<h3 class="code-line" data-line-start=62 data-line-end=63 ><a id="USO_DEL_API_62"></a>USO DEL API</h3>
<p class="has-line-data" data-line-start="63" data-line-end="64">Dentro del API encontrará tres controladores, cada uno con cuatro métodos que se corresponden con las operaciones CRUD (create, read, update and delete).</p>
<h5 class="code-line" data-line-start=65 data-line-end=66 ><a id="1_Character_Controller_65"></a>1. Character Controller</h5>
<p class="has-line-data" data-line-start="66" data-line-end="67"><em>Este controlador está encargado de gestionar los datos que tengan que ver con los personajes.</em></p>
<ul>
<li class="has-line-data" data-line-start="68" data-line-end="85">
<p class="has-line-data" data-line-start="68" data-line-end="70"><strong>Método POST</strong><br>
Ingresar los datos que serán guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo “Character” y guardarlo en la base de datos.</p>
<p class="has-line-data" data-line-start="71" data-line-end="73">Campo <em>name</em>: Obligatorio.<br>
En este campo se ingresa el nombre de personaje. Dicho campo está configurado como una columna única dentro del diseño de la base de datos. Eso quiere decir que no podrán guardarse dos personajes con el mismo nombre.</p>
<p class="has-line-data" data-line-start="74" data-line-end="76">Campo <em>movies</em>: Opcional.<br>
En el caso de este campo registrará la base de datos para evaluar si las películas ingresadas existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.</p>
<p class="has-line-data" data-line-start="77" data-line-end="79">Campo <em>imageUrl</em>: Opcional.<br>
URL de la imagen del personaje que será consumida luego por el cliente.</p>
<p class="has-line-data" data-line-start="80" data-line-end="82">Campo <em>background</em> : Opcional.<br>
Texto sobre la historia del personaje.</p>
<p class="has-line-data" data-line-start="83" data-line-end="85">Campo <em>age</em>: Opcional.<br>
Edad del personaje.</p>
</li>
</ul>
<pre><code>--Campo _weight_: Opcional. 
    Peso del personaje.
</code></pre>
<ul>
<li class="has-line-data" data-line-start="90" data-line-end="97">
<p class="has-line-data" data-line-start="90" data-line-end="94"><strong>Método GET</strong><br>
Se devuelve un JSON con los datos de los personajes guardados en la base de datos.<br>
A este método se pueden pasar parametros para filtrar tales busquedas por nombre, edad o películas en las que aparecen.<br>
<strong>No es posible ingresar mas de un críterio de busqueda a la vez.</strong>  <em>En caso de no especificarse ninguno se devolverán todos los personajes que se encuentren en la base de datos.</em></p>
<p class="has-line-data" data-line-start="95" data-line-end="96">Para devolver los resultados se utiliza un DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y películas).</p>
</li>
<li class="has-line-data" data-line-start="97" data-line-end="103">
<p class="has-line-data" data-line-start="97" data-line-end="102"><strong>Método PUT</strong><br>
Se actualiza un personaje en partícular con información nueva.<br>
Este método es muy similar al POST.<br>
Todos los campos son opcionales salvo el nombre del personaje modificado y el ID del personaje que se quiera modificar.<br>
<strong>Atención:</strong> En caso de no completar ciertos campos se actualizarán como nulos, pudiendo perder así información sobre el personaje.</p>
</li>
<li class="has-line-data" data-line-start="103" data-line-end="106">
<p class="has-line-data" data-line-start="103" data-line-end="105"><strong>Método DELETE</strong><br>
Borra un personaje de la base de datos en base a su ID.</p>
</li>
</ul>
<h5 class="code-line" data-line-start=106 data-line-end=107 ><a id="2_Movie_Controller_106"></a>2. Movie Controller</h5>
<p class="has-line-data" data-line-start="107" data-line-end="108"><em>Este controlador está encargado de gestionar los datos que tengan que ver con las películas.</em></p>
<p class="has-line-data" data-line-start="109" data-line-end="116">Método POST: Ingresar los datos que serán guardados en un DTO(Data Transfer Object) para luego convertirlos en un objeto de tipo “Movie” y guardarlo en la base de datos.<br>
–Campo “name”: Obligatorio. En este campo se ingresa el nombre de la película. Dicho campo está configurado como una columna única dentro del diseño de la base de datos. Eso quiere decir que no podrán guardarse dos películas con el mismo nombre.<br>
–Campo “characters”: Opcional. En el caso de este campo registrará la base de datos para evaluar si los personajes ingresados existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.<br>
–Campo “genres”: Opcional. En el caso de este campo registrará la base de datos para evaluar si los géneros ingresados existen, en caso contrario creará las respectivas instancias en la base de datos, las cuales tendrán todos sus campos nulos, salvo el nombre.<br>
–Campo “imageUrl”: Opcional. URL de la imagen de la película que será consumida luego por el cliente.<br>
–Campo “rating” :Opcional. Evaluación de la película en base a un críterio de 5 opciones.<br>
–Campo “release date”: Opcional. Fecha de estreno de la película.</p>
<p class="has-line-data" data-line-start="117" data-line-end="119">Método GET: Se devuelve un JSON con los datos de las películas guardadas en la base de datos.<br>
A este método se pueden pasar parametros para filtrar tales busquedas por nombre, género o alterar el orden en el que aparecen en la lista. No es posible ingresar mas de un críterio de busqueda a la vez.  En caso de no especificarse ninguno se devolverán todos las películas que se encuentren en la base de datos.</p>
<p class="has-line-data" data-line-start="120" data-line-end="121">Para devolver los resultados utilizo otro DTO que separa el nombre y la imagen del personaje de sus detalles (edad, peso, historia y películas).</p>
<p class="has-line-data" data-line-start="122" data-line-end="124">Método PUT: Se actualiza una película en partícular con información nueva. Este método es muy similar al POST. Todos los campos son opcionales salvo el nombre de la película modificada y el ID de la película que se quiera modificar.<br>
Atención: En caso de no completar ciertos campos se actualizarán como nulos, pudiendo perder así información sobre la película.</p>
<p class="has-line-data" data-line-start="125" data-line-end="126">Método DELETE: Borra una película de la base de datos en base a su ID.</p>
