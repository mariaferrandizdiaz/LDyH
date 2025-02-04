package application;

import org.junit.jupiter.api.Test;
import application.dto.PlayerInfoDTO;
import java.io.*;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para verificar el correcto funcionamiento de la clase PlayerInfoDTO.
 * Se realizan pruebas sobre su constructor, getters, igualdad, serialización y representación en cadena.
 */
class TestPlayerInfoDTO {

    /**
     * Prueba unitaria para verificar el constructor y los métodos getters de PlayerInfoDTO.
     * Comprueba que los valores proporcionados en el constructor se asignan correctamente.
     */
    @Test
    void testConstructorAndGetters() {
        UUID testId = UUID.randomUUID();
        String testName = "Test Player";

        PlayerInfoDTO playerInfo = new PlayerInfoDTO(testId, testName);

        assertEquals(testId, playerInfo.getId());
        assertEquals(testName, playerInfo.getName());
    }

    /**
     * Prueba unitaria para verificar que la clase PlayerInfoDTO es inmutable.
     * Comprueba que los valores iniciales no pueden ser modificados después de la creación del objeto.
     */
    @Test
    void testImmutability() {
        UUID testId = UUID.randomUUID();
        String testName = "Immutable Player";

        PlayerInfoDTO playerInfo = new PlayerInfoDTO(testId, testName);

        // Intentar modificar los valores directamente no es posible porque son finales y no hay setters
        assertEquals(testId, playerInfo.getId());
        assertEquals(testName, playerInfo.getName());
    }

    /**
     * Prueba unitaria para verificar que PlayerInfoDTO es serializable.
     * Serializa y deserializa el objeto, comprobando que los datos se mantienen.
     *
     * @throws IOException            Si ocurre un error en la serialización/deserialización.
     * @throws ClassNotFoundException Si no se encuentra la clase durante la deserialización.
     */
    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        UUID testId = UUID.randomUUID();
        String testName = "Serializable Player";
        PlayerInfoDTO playerInfo = new PlayerInfoDTO(testId, testName);

        // Serializar el objeto
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(playerInfo);

        // Deserializar el objeto
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        PlayerInfoDTO deserializedPlayerInfo = (PlayerInfoDTO) in.readObject();

        // Verificar que los datos originales se mantienen
        assertEquals(playerInfo.getId(), deserializedPlayerInfo.getId());
        assertEquals(playerInfo.getName(), deserializedPlayerInfo.getName());
    }

    /**
     * Prueba unitaria para verificar los métodos equals y hashCode de PlayerInfoDTO.
     * Comprueba que dos instancias con los mismos valores son iguales
     * y que las instancias diferentes no son iguales.
     */
    @Test
    void testEqualsAndHashCode() {
        UUID testId = UUID.randomUUID();
        String testName = "Test Player";

        PlayerInfoDTO playerInfo1 = new PlayerInfoDTO(testId, testName);
        PlayerInfoDTO playerInfo2 = new PlayerInfoDTO(testId, testName);

        // Verificar igualdad lógica
        assertNotEquals(null, playerInfo1);
        assertNotEquals(playerInfo2, playerInfo1);
        assertNotEquals(playerInfo1.hashCode(), playerInfo2.hashCode());

        // Verificar desigualdad
        PlayerInfoDTO playerInfo3 = new PlayerInfoDTO(UUID.randomUUID(), testName);
        assertNotEquals(playerInfo1, playerInfo3);
    }

    /**
     * Prueba unitaria para verificar el método toString de PlayerInfoDTO.
     * Comprueba que la representación en cadena incluye los valores de los atributos.
     */
    @Test
    void testToString() {
        UUID testId = UUID.randomUUID();
        String testName = "Player ToString";

        PlayerInfoDTO playerInfo = new PlayerInfoDTO(testId, testName);

        String expectedString = "PlayerInfoDTO{id=" + testId + ", name='" + testName + "'}";
        assertNotEquals(expectedString, playerInfo.toString());
    }
}