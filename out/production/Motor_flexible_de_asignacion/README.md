# Motor Flexible de Asignación de Descuentos

## Descripción del Proyecto y Patrón Implementado

Este repositorio contiene la refactorización de un motor encargado de calcular descuentos para la compra de planes turísticos en la plataforma Turismo Cundinamarca.

En su estado inicial, la clase `CalculadorDescuento` utilizaba una estructura condicional `if-else if` y cadenas de texto para determinar cuál algoritmo de descuento debía ejecutar. Las modalidades disponibles eran cliente frecuente, temporada baja y convenio empresarial.

Este diseño generaba alto acoplamiento, responsabilidades mezcladas y dependencia de cadenas de texto como `"FRECUENTE"`, `"TEMPORADA_BAJA"` y `"CONVENIO"`. Además, cada nueva política de descuento obligaba a modificar el método principal de cálculo, aumentando el riesgo de introducir errores en las funcionalidades existentes.

Para solucionar estos problemas de diseño, se implementó el patrón de comportamiento **Strategy**.

La implementación de este patrón permite encapsular cada algoritmo de descuento en una clase independiente que implementa la interfaz común `EstrategiaDescuento`. La clase `CalculadorDescuento` actúa como contexto y delega la operación de cálculo a la estrategia configurada, sin conocer los detalles internos de cada algoritmo.

Las estrategias implementadas son:

- `DescuentoFrecuente`, que aplica un descuento del 10 %.
- `DescuentoTemporadaBaja`, que aplica un descuento del 15 %.
- `DescuentoConvenio`, que aplica un descuento del 20 %.

La estrategia activa puede seleccionarse o reemplazarse mediante el método `setEstrategia()`. Esto permite cambiar el algoritmo de descuento durante la ejecución sin modificar la clase `CalculadorDescuento`.

La refactorización permite cumplir con el principio Open/Closed (OCP), debido a que el sistema puede incorporar nuevas políticas creando clases adicionales que implementen `EstrategiaDescuento`, sin modificar el contexto ni las estrategias existentes.

También mejora el cumplimiento del principio de responsabilidad única (SRP), porque cada estrategia concreta contiene únicamente su algoritmo de descuento, y del principio de inversión de dependencias (DIP), porque `CalculadorDescuento` depende de la abstracción `EstrategiaDescuento` y no de implementaciones concretas.

## Requisitos Técnicos

- **JDK:** 17 o superior.
- **IDE recomendado:** IntelliJ IDEA, Eclipse o Visual Studio Code.
- **Framework de pruebas:** JUnit 5.
- **Dependencias:** JUnit Jupiter para las pruebas unitarias.
- **Lenguaje de programación:** Java.
- **Patrón GoF implementado:** Strategy.
- **Categoría GoF:** Comportamiento.

Los participantes del patrón Strategy son:

- **Strategy:** `EstrategiaDescuento`.
- **ConcreteStrategy:** `DescuentoFrecuente`.
- **ConcreteStrategy:** `DescuentoTemporadaBaja`.
- **ConcreteStrategy:** `DescuentoConvenio`.
- **Context:** `CalculadorDescuento`.
- **Client:** componente o prueba que selecciona la estrategia de descuento aplicable.

## Instrucciones para Compilar y Ejecutar el Proyecto

- Clone este repositorio en su entorno local ejecutando:

```bash
git clone https://github.com/Carrillo524/Patrones_GoF_Caso3_Motor_flexible_de_asignacion
```

- Abra su IDE, por ejemplo IntelliJ IDEA, Eclipse o Visual Studio Code.

- Seleccione la opción **Open** o **Import Project** para importar la carpeta raíz del proyecto.

- Verifique que el IDE esté utilizando el SDK de Java 17 o una versión superior.
- Compruebe que tenga JUnit instalado en el IDE.

- Compruebe que el archivo `CalculadorDescuentoTest.java` esté listo para ejecutarse.

- Ejecute la prueba unitaria.

Dado que este proyecto corresponde a un módulo de lógica de negocio sin interfaz gráfica, la ejecución y validación de sus funcionalidades se realiza principalmente mediante la suite de pruebas unitarias.

El funcionamiento del sistema consiste en crear un objeto de tipo `CalculadorDescuento`, asignarle una implementación de `EstrategiaDescuento` y ejecutar el método `calcular()`.

Ejemplo de uso:

```java
CalculadorDescuento calculador = new CalculadorDescuento();

calculador.setEstrategia(new DescuentoFrecuente());

double resultado = calculador.calcular(1000.0);

System.out.println(resultado);
```

La salida esperada para este ejemplo es:

```text
100.0
```

La estrategia puede reemplazarse durante la ejecución:

```java
calculador.setEstrategia(new DescuentoTemporadaBaja());

double resultadoTemporadaBaja = calculador.calcular(1000.0);

System.out.println(resultadoTemporadaBaja);
```

La salida esperada es:

```text
150.0
```

La clase `CalculadorDescuento` valida que exista una estrategia antes de realizar la delegación. Si se intenta calcular un descuento sin configurar una estrategia, se lanza una excepción de tipo `IllegalStateException`.

## Instrucciones para correr la Suite de Pruebas (JUnit 5)

- En el panel explorador de su IDE, diríjase al directorio `src/test/java`.

- Abra el archivo de pruebas `CalculadorDescuentoTest.java`.

- Haga clic en el ícono verde de **Play** o **Run** ubicado junto a la declaración de la clase.

- En IntelliJ IDEA, también puede ejecutar la suite utilizando la opción **Run CalculadorDescuentoTest**.

- Si utiliza Windows con IntelliJ IDEA, puede ejecutar las pruebas mediante el atajo `Ctrl+Shift+F10`.

- Se ejecutará el conjunto completo de pruebas definido con JUnit 5.

La suite valida los siguientes escenarios:

- Aplicación del 10 % de descuento mediante `DescuentoFrecuente`.
- Aplicación del 15 % de descuento mediante `DescuentoTemporadaBaja`.
- Aplicación del 20 % de descuento mediante `DescuentoConvenio`.
- Lanzamiento de una excepción cuando se intenta calcular sin configurar una estrategia.
- Reemplazo de la estrategia durante la ejecución.

Para una compra con valor de `1000.0`, los resultados esperados son:

- `DescuentoFrecuente`: `100.0`.
- `DescuentoTemporadaBaja`: `150.0`.
- `DescuentoConvenio`: `200.0`.

Cuando no se configura una estrategia, el sistema debe lanzar una excepción de tipo `IllegalStateException` con el siguiente mensaje:

```text
Debe configurar una estrategia antes de calcular
```

Verifique en la consola de JUnit que todos los escenarios finalicen exitosamente y aparezcan marcados en color verde.

Las tres primeras pruebas permiten comprobar que los resultados funcionales del sistema original se conservan después de la refactorización. Las pruebas adicionales comprueban la configuración obligatoria y el reemplazo dinámico de estrategias.

## Guía de Actividad Realizada

- Importación y comprobación de compilación del código base.
- Análisis de la estructura original de `CalculadorDescuento`.
- Identificación de condicionales rígidos en el método `calcular()`.
- Identificación del uso de cadenas de texto para controlar la selección de algoritmos.
- Identificación de responsabilidades mezcladas dentro de `CalculadorDescuento`.
- Construcción de la línea base de pruebas con JUnit 5.
- Validación de los descuentos originales para cliente frecuente, temporada baja y convenio.
- Selección del patrón de comportamiento Strategy.
- Creación de la interfaz `EstrategiaDescuento`.
- Creación de la estrategia concreta `DescuentoFrecuente`.
- Creación de la estrategia concreta `DescuentoTemporadaBaja`.
- Creación de la estrategia concreta `DescuentoConvenio`.
- Refactorización de `CalculadorDescuento` como contexto del patrón Strategy.
- Incorporación del método `setEstrategia()` para permitir la sustitución de algoritmos.
- Eliminación de los condicionales utilizados para seleccionar modalidades de descuento.
- Eliminación de las cadenas de texto utilizadas para controlar el flujo de cálculo.
- Delegación del cálculo mediante la interfaz `EstrategiaDescuento`.
- Incorporación de una validación para impedir la ejecución sin una estrategia configurada.
- Adaptación de las pruebas unitarias al diseño refactorizado.
- Validación de regresión de los tres algoritmos originales.
- Validación de la sustitución dinámica de estrategias.
- Preparación del sistema para incorporar nuevas políticas de descuento sin modificar las clases existentes.