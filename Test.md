Junit

Permite controlar los test de regresion, es decir, cuando cambiamos parte del codigo y queremos volver a comprobar que sigue funcionando

Esta compuesto de varios modulos (Junit Platform, Jupiter, Vintage)

Platform, es la responsable de lanzar el framerwork de pruebas en la java virtual machine.
Jupiter
Vintage, permite lanzar test de versiones anteriores

· Agregamos dependencias al POM
buscar la dependencia en el sitio web de Maven Central Repository:
- Busca la librería y copia el bloque de código XML.
    junit-jupiter-engine
    junit-jupiter-api


Al crear un metodo en un fichero java, en vscode podemos dar click derecho, usar source action y dar en Generate Test

Entonces a partir de unas condiciones esperaremos un resultado esperado. esto es lo que aseguraremos en los ficheros test.

Son test unitarios, por que se prueba las unidades minimas

