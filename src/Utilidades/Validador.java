package Utilidades;

public class Validador {
    /**
     * Verifica si el texto ingresado representa un número válido (decimal o entero).
     *
     * @param num El texto a verificar.
     * @return true si el texto es un número válido, false en caso contrario.
     */
    public static boolean isNaN(String num){
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Verifica si el texto ingresado representa un valor vacio o nulo.
     *
     * @param input El texto a verificar.
     * @return true si el texto esta vacio o nulo, false en caso contrario.
     */
    public static boolean isEmpty(String input){
        return input == null || input.trim().isEmpty();
    }
}
