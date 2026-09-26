# Motor Flexible de Asignación de Descuentos

## Descripción del Proyecto y Patrón Implementado

Este repositorio contiene la refactorización de un motor encargado de calcular descuentos para la compra de planes turísticos en la plataforma Turismo Cundinamarca.

En su estado inicial, la clase `CalculadorDescuento` utilizaba una estructura condicional `if-else if` y cadenas de texto para determinar cuál algoritmo de descuento debía ejecutar. Las modalidades disponibles eran cliente frecuente, temporada baja y convenio empresarial.

Este diseño generaba alto acoplamiento, responsabilidades mezcladas y dependencia de cadenas de texto como `"FRECUENTE"`, `"TEMPORADA_BAJA"` y `"CONVENIO"`. Además, cada nueva política de descuento obligaba a modificar el método principal de cálculo, aumentando el riesgo de introducir errores en las funcionalidades existentes.

Para solucionar estos problemas de diseño, se implementó el patrón de comportamiento **Strategy**.

La implementación de este patrón permite encapsular cada algoritmo de descuento en una clase independiente que implementa la interfaz común `EstrategiaDescuento`. La clase `CalculadorDescuento` actúa como contexto y delega la operación de cálculo a la estrategia configurada, sin conocer los detalles internos de cada algoritmo.

Las estrategias base implementadas originalmente son:

- `DescuentoFrecuente`, que aplica un descuento del 10 %.
- `DescuentoTemporadaBaja`, que aplica un descuento del 15 %.
- `DescuentoConvenio`, que aplica un descuento del 20 %.

### Integración de Cambios Futuros (Nuevos Escenarios Empresariales)

Para dar respuesta al anuncio del área de mercadeo sobre la aparición frecuente de nuevas políticas, se integraron los siguientes algoritmos adicionales, demostrando la capacidad de extensión y flexibilidad del sistema sin alterar el núcleo principal:

- `DescuentoAniversario`, para la campaña de aniversario.
- `DescuentoRegional`, para promociones regionales.
- `DescuentoMunicipio`, para descuentos focalizados por municipio.
- `DescuentoTemporal`, para activaciones de promociones temporales.
- `DescuentoCajaCompensacion`, para el manejo de alianzas con cajas de compensación.

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

Los participantes del patrón Strategy en este proyecto son:

- **Strategy:** `EstrategiaDescuento`.
- **ConcreteStrategy:** `DescuentoFrecuente`, `DescuentoTemporadaBaja`, `DescuentoConvenio` y las nuevas estrategias de mercadeo (`DescuentoAniversario`, `DescuentoRegional`, etc.).
- **Context:** `CalculadorDescuento`.
- **Client:** componente o prueba que selecciona la estrategia de descuento aplicable.

## Instrucciones para Compilar y Ejecutar el Proyecto

1. Clone este repositorio en su entorno local ejecutando:

```bash
git clone https://github.com/Carrillo524/Patrones_GoF_Caso3_Motor_flexible_de_asignacion
```

2. Abra su IDE, por ejemplo IntelliJ IDEA, Eclipse o Visual Studio Code.

3. Seleccione la opción **Open** o **Import Project** para importar la carpeta raíz del proyecto.

4. Verifique que el IDE esté utilizando el SDK de Java 17 o una versión superior.

5. Compruebe que tenga JUnit instalado en el IDE.

6. Compruebe que el archivo `CalculadorDescuentoTest.java` esté listo para ejecutarse.

7. Ejecute la prueba unitaria.

Dado que este proyecto corresponde a un módulo de lógica de negocio sin interfaz gráfica, la ejecución y validación de sus funcionalidades se realiza principalmente mediante la suite de pruebas unitarias.

El funcionamiento del sistema consiste en crear un objeto de tipo `CalculadorDescuento`, asignarle una implementación de `EstrategiaDescuento` y ejecutar el método `calcular()`.

La clase `CalculadorDescuento` valida que exista una estrategia antes de realizar la delegación. Si se intenta calcular un descuento sin configurar una estrategia, se lanza una excepción de tipo `IllegalStateException`.

## Instrucciones para Correr la Suite de Pruebas (JUnit 5)

1. En el panel explorador de su IDE, diríjase al directorio `src/test/java`.

2. Abra el archivo de pruebas `CalculadorDescuentoTest.java`.

3. Haga clic en el ícono verde de **Play** o **Run** ubicado junto a la declaración de la clase.

4. En IntelliJ IDEA, también puede ejecutar la suite utilizando la opción **Run CalculadorDescuentoTest**.

5. Si utiliza Windows con IntelliJ IDEA, puede ejecutar las pruebas mediante el atajo `Ctrl+Shift+F10`.

6. Se ejecutará el conjunto completo de pruebas definido con JUnit 5.

La suite valida los siguientes escenarios base y extendidos:

- Aplicación de los descuentos iniciales (`DescuentoFrecuente`, `DescuentoTemporadaBaja` y `DescuentoConvenio`).
- Aplicación exitosa de las cinco nuevas reglas de negocio requeridas por el área de mercadeo: aniversario, regional, municipio, temporal y caja de compensación.
- Lanzamiento de una excepción cuando se intenta calcular sin configurar una estrategia.
- Reemplazo de la estrategia durante la ejecución para certificar el polimorfismo dinámico.

Verifique en la consola de JUnit que todos los escenarios finalicen exitosamente y aparezcan marcados en color verde.

Las primeras pruebas permiten comprobar que los resultados funcionales del sistema original se conservan después de la refactorización. Las pruebas adicionales comprueban la escalabilidad exitosa del proyecto.

## Guía de Actividad Realizada

- Importación, compilación y análisis del código base.
- Identificación de condicionales rígidos, cadenas de texto y responsabilidades mezcladas.
- Construcción de la línea base de pruebas con JUnit 5.
- Refactorización del sistema mediante el patrón Strategy.
- Actualización de las pruebas unitarias y validación de regresión total.
- Verificación de la extensibilidad para incorporar nuevas políticas sin modificar las clases existentes.