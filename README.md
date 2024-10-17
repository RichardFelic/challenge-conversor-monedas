# challenge-conversor-monedas

Este proyecto es una aplicación de consola en Java que permite convertir una cantidad de dinero de una moneda base a una moneda objetivo utilizando tasas de conversión actualizadas desde una API de terceros.

## Características

- Convertir entre diferentes monedas utilizando tasas de conversión reales.
- Validación de entradas para asegurar que las monedas introducidas son válidas (códigos ISO 4217).
- Continuación de conversiones múltiples hasta que el usuario decida salir.
- Manejo de excepciones en caso de errores al obtener la tasa de conversión o entradas incorrectas.

## Clases Principales

- `Principal`: Clase principal donde se solicita al usuario ingresar la moneda base, la moneda objetivo y la cantidad de dinero que desea convertir. Utiliza las clases `Validador` y `ApiClient` para manejar la lógica de validación y obtención de tasas de conversión, respectivamente.
- `ApiClient`: Clase encargada de conectarse a la API de tasas de conversión y obtener la tasa actual para una determinada pareja de monedas.
- `Validador`: Clase que contiene métodos de validación para entradas de usuario, asegurando que los códigos de monedas y las cantidades sean correctas.

## API utilizada

Para obtener las tasas de conversión en tiempo real, este proyecto utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/). Esta API permite consultar las tasas de cambio entre una moneda base y una moneda objetivo utilizando su código ISO 4217.

### Ejemplo de uso de la API

La API devuelve la tasa de conversión entre dos monedas en formato JSON. Aquí un ejemplo de cómo se hace la solicitud:

https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD

Esta URL obtendría las tasas de conversión desde el USD (dólar estadounidense) a todas las demás monedas soportadas. Luego, el programa extrae la tasa específica para la moneda objetivo y realiza la conversión.

## Requisitos

- Java 8 o superior.
- Una clave de API válida de [ExchangeRate-API](https://www.exchangerate-api.com/).
- La dependencia [JSON](https://repo1.maven.org/maven2/org/json/json/20240303/json-20240303.jar)

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/RichardFelic/challenge-conversor-monedas.git
   ```
2. Agregar tu clave de API en la clase ApiClient.
3. Compilar y ejecutar el proyecto.

## Uso
Al ejecutar el programa, el usuario deberá ingresar:

1. La moneda base (por ejemplo, USD).
2. La moneda objetivo (por ejemplo, EUR).
3. La cantidad de dinero a convertir.

El programa mostrará la tasa de conversión y el resultado de la conversión.

## Contribuciones

Las contribuciones son bienvenidas. Siéntete libre de hacer un fork del proyecto y enviar un pull request.
