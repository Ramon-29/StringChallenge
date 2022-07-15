import java.util.Arrays;

public class StringChallenge {
    /*
    * Have the function StringChallenge(str) read str which will contain two strings
    * separated by a space. The first string will consist of the following sets of characters:+,
    * *,$,and {N} which is optional. The plus (+) character represents a single alphabetic
    * character, the {$} character represents a number between 1-9, and the asterisk(*)
    * represents a sequence of the same character of length 3 unless it is followed by {N}
    * which represents how many characters should appear in the sequence where N will be
    * at least 1.Your goal is to determine if the second string exactly matches the pattern of
    * the first string in the input
    *
    * For example: if str is "++*{5} jtggggg" then the second string in this case does match
    * the pattern, so your program should return the string true. If the second striung does not
    * match the pattern your program should return the string false.
    *
    * */
    public static void main(String[] args) {
        String  entrada = "$+**{2}**{4} 1axxxbbccchhhh" ;
        System.out.println("\nEntrada: " + entrada);
        System.out.println("\n El resultado es: " + Challenge(entrada));
    }

    public static String Challenge(String entrada){
        if (Arrays.stream(entrada.split(" ")).count()!=2){
            try {
                throw new Exception("Patron no valido");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            boolean valido = true;

            String pattern = entrada.split(" ")[0];
            String cadena = entrada.split(" ")[1];


            int indiceCadena = 0;
            boolean error = false;
            int i = 0;
            while (i < pattern.length() && valido) {
                if (pattern.charAt(i) == '$') {
                    valido = esNumero(cadena.charAt(indiceCadena));
                    indiceCadena++;
                } else if (pattern.charAt(i) == '+') {
                    valido = esLetra(cadena.charAt(indiceCadena));
                    indiceCadena++;
                } else if (pattern.charAt(i) == '*') {
                    if (i + 3 < pattern.length()) {
                        String bloqueRep = pattern.substring(i + 1, i + 4);
                        if (bloqueRep.matches("\\{([0-9])+\\}")) {
                            int ocurrencias = Integer.parseInt(String.valueOf(bloqueRep.charAt(1)));
                            valido = cadenaIgualLetra(cadena.substring(indiceCadena, indiceCadena + ocurrencias), ocurrencias);
                            i += 3;
                            indiceCadena += ocurrencias;
                        } else {
                            valido = cadenaIgualLetra(cadena.substring(indiceCadena, indiceCadena + 3), 3);
                            indiceCadena += 3;
                        }
                    } else {
                        if (indiceCadena + 2 <= cadena.length()) {
                            valido = cadenaIgualLetra(cadena.substring(indiceCadena, indiceCadena + 3), 3);
                            indiceCadena += 3;
                        } else {
                            valido = false;
                        }

                    }
                }
                /*
                 *   El formato del patrón no es válido
                 * */
                else {
                    try {
                        throw new Exception("Patron no valido");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                i++;
            }
            if (indiceCadena != cadena.length())
                valido = false;
            return valido ? "true" : "false";
        }
    }

    public static boolean esNumero(char caracter){
        return Character.isDigit(caracter);
    }

    public static boolean esLetra(char caracter){
        return !Character.isDigit(caracter);
    }

    public static boolean cadenaIgualLetra(String cadena, int ocurrencias){
        boolean valida = cadena.length()==ocurrencias;
        int i=1;
        while(i<ocurrencias && valida){
            if(cadena.charAt(i)!=cadena.charAt(0))
                valida = false;
            i++;
        }

        return valida;
    }
}
