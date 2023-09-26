## Springboot + MongoDB

Repositorio con un ejemplo donde integramos apis Springboot con una BD de prueba en MongoDB

En este Proyecto se implementan operaciones CRUD para administrar items de compras del supermercado (ficticio), las operaciones disponibles son:

 - Crear items de supermercado, indicando la categoria, cantidad y nombre
 - Listar todos
 - Listar por categoria
 - Eliminar el item por nombre
 - eliminar items por Nombre de Categoria
 - Rrenombrar categoria en forma masiva

La BD Mongo DB esta operativa y la configuración para leer/escribir esta en los properties (incluye la password).
El proyeco esta realizado con Eclipse y el framework Springboot, solo necesitas descargarlo y correrlo localmente.
Esta dispobible el set de llamadas a las API rest con postman, importar las llamadas desde ./postman/Contract Pruebas.Springboot+MongoDB.json


RELEASE 2023-09-26 

Disponible proyecto funcional y pruebas con postman, se configuro acceso a BD Mongo DB desde cualquier IP.

Por ejemplo:

1) Si ejecutas listar todos los items de supermercado obteneras la lista disponible a la fecha, por ejemplo una respuesta es:

Consulta y respuesta:
![image](https://github.com/jcotrado/MongoDB-Springboot/assets/25447366/03e57262-bdc2-419a-b1ea-9fafa00ae93e)

2) Insertar un nuevo item:

Consulta

![image](https://github.com/jcotrado/MongoDB-Springboot/assets/25447366/42f51ebc-b57c-436e-b466-c0f72ff2c2aa)

{
    "nombre":"Lonco Leche - Natural - Sin Lactosa",
    "cantidad":50,
    "categoria":"Lacteos"
}

Respuesta

![image](https://github.com/jcotrado/MongoDB-Springboot/assets/25447366/e0eb4eb5-ea28-4076-b093-a62a68415aba)

{
    "id": "6512d9b3a0fe7a19257259ef",
    "nombre": "Lonco Leche - Natural - Sin Lactosa",
    "cantidad": 50,
    "categoria": "Lacteos"
}









