package cl.lupoconecta.apijava.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    // @Test indica que es un method de prueba
    // Dar nombres claros a los methods
    // message cuando es necesario explicar el error

    /*
     //METODO////////////////////////////////////////ESPECIFICACIOM//////////////////////////////////////////////////////////////////////////////
    int sumar(                  |Este método devuelve un int resultado de la suma de numero 1 y numero2
        int numero1,            |
        int numero2)            |
    ------------------------------------------------------------------------------------------------------------------------
    int restar(                 |Este método devuelve un int resultado de la resta de numero 1 y numero2
        int numero1,            |
        int numero2)            |


    Método a Probar                 |      Entrada      |       Salida Esperada
    sumar(int a, int b)             |a = 10, b=20       |30
    sumar(int a, int b)             |a = 7, b=4         |11
    restar(int a, int b)            |a = 7, b=4         |3
    restar(int a, int b)            |a = 10, b=20       |-10
     */
    private Calculator calculator;
    private Calculator calculatorNull;
    /*
        Etiqueta @BeforeEach indica que el método se ejecutará antes de cada método de prueba.
        Esto es útil para configurar el estado necesario para las pruebas,
        como inicializar objetos o establecer condiciones previas, datos de entrada y salida.
    */
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    /*
        Etiqueta @AfterEach indica que el método se ejecutará después de cada método de prueba.
        Esto es útil para limpiar o restablecer el estado después de cada prueba,
        como liberar recursos, cerrar conexiones o restablecer variables a su estado original.
    */
    @AfterEach
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void calculatorNotNullTest() {
        assertNotNull(calculator, "Calculator should not be null");
    }

    @Test
    public void calculatorNullTest() {
        assertNull(calculatorNull, "Calculator should be null");
    }

    /*
        Los asset son afirmaciones que verifican si una condición es verdadera o falsa.
        Si la condición es verdadera, la prueba pasa; si es falsa, la prueba falla.
        Los asset se utilizan para validar los resultados esperados de un método o función.
    */

    @Test
    public void addTest() {
        // Setup
        int expectedResult = 30;
        // Execution
        int result = calculator.add(10, 20);
        // Verification Assert
        assertNotNull(result, "Result should not be null");
        //assert(result == expectedResult);
        assertEquals(expectedResult, result, "Addition result should be 30");
    }
    /* Esto anterior se puede optimizar dejando
        assertEquals(30, calculator.add(10, 20), "Addition result should be 30");
    */
}
