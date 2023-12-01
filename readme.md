# Mendel Backend Challenge

## Instalación

### Descargar la imagen desde Dockerhub:
```bash
docker pull gdeschant/mendel-backend-challenge
```

### O construir la imagen localmente:
```bash
docker build -t mendel-backend-challenge .
```

### Iniciar el contenedor:
```bash
docker run -it -p 8080:8080 mendel-backend-challenge
```

## Detalles de la Implementación

Durante la implementación de la API, se tomaron varias decisiones de diseño para cumplir con su alcance y requisitos.

- Estructura del proyecto: controller -> service -> persistence. Aunque la capa de servicio maneje escasa lógica en este caso, se considera buena práctica aislar la capa de persistencia o repositorio de los controladores. Si se introdujera lógica de negocio, residiría en la capa de servicio.
- Almacenamiento de datos de transacciones: se utiliza un HashMap con el ID de la transacción como clave y el objeto 'Transaction' como valor.
- Equilibrio entre inserción y recuperación de datos: se priorizó la creación de transacciones en tiempo constante, sacrificando rendimiento para la recuperación de datos, especialmente en GET /transactions/sum/{transaction_id}. En un escenario real, esta decisión se tomaría en función de los requisitos funcionales y no funcionales.
- Uso de la librería Lombok: elegida por la simplicidad que aporta al registro e implementación del modelo 'Transaction'.
