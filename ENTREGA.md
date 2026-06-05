# ENTREGA

Antonio Manuel Pérez Gómez

> Sustituye cada `TODO` por tu respuesta.
> Usa enlaces permanentes de GitHub cuando se pidan enlaces al código.

## Descripción breve de la solución

He realizado una solución incompleta de la aplicación, he retirado la generacion de partidos y he realizado solo hasta la escritura en CSV de las parejas y la realizacion de estas. La solución es simple y utiliza la estructura ya otorgada por el profesor e implementa un repositorio para el almacenamiento de los archivos de los jugadores, un parseador que lee estos mismos archivos y los "descuartiza" en varias lineas para facilitar la lectura de todos los jugadores y sus nombres, un normalizador de los niveles para facilitar la lectura de nivel de cada jugador para luego emparejarlos con otra clase , y por ultimo una clase encargada de escribir en un archivo aparte los datos de las parejas realizadas.

## Ejemplo de ejecución

./gradlew run --args"--torneo <NombreDelTorneo> -- path ./test/resources/jugadores-ejemplo"

### Fichero de parejas

Debido a que no esta completo el programa no da el archivo de parejas ya que para poder devolverlo es necesario según la lógica que he aplicado
```text
TODO
```

### Fichero de partidos

```text
TODO
```

### Salida por consola con incidencias

```text
TODO
```

### Carpeta `procesados` con los ficheros movidos

TODO: Evidencia o captura de pantalla.

## Preguntas: COMPLEMENTA LAS RESPUESTAS CON ENLACES PERMANENTES DE GITHUB

### [CE 5.a] 1.a. Muestra cómo tu programa recibe y utiliza los argumentos `--torneo` y `--path`.

Incluye:

- Enlace permanente al código donde se procesan los argumentos.
  TODO
- Breve explicación.
  TODO
- Ejemplo de ejecución real con comando y salida por consola.
  TODO

### [CE 5.b] 2.a. Muestra la salida completa por consola tras procesar varios ficheros.

Explica brevemente y no olvides enlaces permanentes al código:

- Qué información muestras.
  TODO
- Cómo has estructurado el formato para que sea legible.
  TODO
- Ejemplo de ejecución real con comando y salida por consola.
  TODO

### [CE 5.c] 3.a. Indica qué clases o métodos has utilizado para trabajar con ficheros y por qué los has elegido.

Incluye:

- Enlace permanente al código donde se ejemplifica su uso.
  TODO
- Descripción del código anterior.
  TODO
- Justificación de por qué usas esas clases o métodos y no otros.
  TODO

### [CE 5.d] 4.a. Muestra cómo interpretas el formato del fichero de entrada y cómo validas que sea correcto.

Incluye:

- Enlace permanente al código de lectura y validación.
  TODO
- Descripción del código anterior.
  TODO
- Un ejemplo de error detectado por tu programa y cómo se gestiona.
  TODO

### [CE 5.e] 5.a. Breve comentario sobre tu código, con enlaces permanentes, acerca de cómo realizas:

- Describe la lectura de ficheros.
  TODO
- Describe la escritura de resultados CSV y TXT.
  TODO
- Describe el movimiento de ficheros a la carpeta `procesados`.
  TODO

Incluye un enlace permanente a cada caso y una breve explicación.

## Checklist final

- [ ] He rellenado todos los `TODO`.
- [ ] He añadido enlaces permanentes de GitHub.
- [ ] He incluido ejemplos reales de ejecución y salida.
- [ ] He revisado el formato final.
