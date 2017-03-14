DATA LAYER

Es la capa en donde se obtienen todos los datos que necesita nuestra aplicación para funcionar y los datos pueden ser de proveídos por una base de datos, de la red, de la memoria, etc. En android cuando así lo requeira, haremos uso de Repository Pattern para poder abstraer el origen de datos.

Cada capa deberá tener su propio modelo de datos, ya que esto permitirá que sea más fácil de testar y modificar.

DOMAIN LAYER

Toda la lógica de negocio ocurre en esta capa, aquí solo lo enfocado a que tiene que hacer nuestra aplicación. Se encuentran entidades y casos de uso (son los encargados de implementar lógica de negocio de nuestra aplicación, también se conocen como interactores).

PRESENTATION LAYER

Es la capa en donde ocurre todo lo relacionado a cómo funcionan la vistas normalmente activities y fragmentos los cuales contienen lógica, es decir el que debo mostrarle al usuario y cuando debe hacerlo. 
