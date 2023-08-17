import java.util.Random;

public class LastNameGenerator {
    public static String generateRandomLastName(Gender gender, boolean isCouple) {
        if (gender == Gender.FEMALE && isCouple) {
            return "Compartido";
        } else {
            String[] maleLastNames = {

                    "González", "Rodríguez", "Pérez", "Fernández", "López", "Martínez", "García", "Hernández", "Martín", "Jiménez",
                    "Ruiz", "Díaz", "Moreno", "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez", "Navarro", "Torres",
                    "Domínguez", "Vázquez", "Ramos", "Gil", "Ramírez", "Serrano", "Blanco", "Molina", "Morales", "Ortega",
                    "Delgado", "Castro", "Ortiz", "Núñez", "Garrido", "Santiago", "Prieto", "Méndez", "Flores", "Iglesias",
                    "Diez", "Cruz", "Luna", "Calvo", "Peña", "Cabrera", "Herrera", "Cortés", "Acosta", "Aguilar"
            };
            String[] femaleLastNames = {

                    "González", "Rodríguez", "Pérez", "Fernández", "López", "Martínez", "García", "Hernández", "Martín", "Jiménez",
                    "Ruiz", "Díaz", "Moreno", "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez", "Navarro", "Torres",
                    "Domínguez", "Vázquez", "Ramos", "Gil", "Ramírez", "Serrano", "Blanco", "Molina", "Morales", "Ortega",
                    "Delgado", "Castro", "Ortiz", "Núñez", "Garrido", "Santiago", "Prieto", "Méndez", "Flores", "Iglesias",
                    "Diez", "Cruz", "Luna", "Calvo", "Peña", "Cabrera", "Herrera", "Cortés", "Acosta", "Aguilar"
            };
            String[] selectedLastNames = (gender == Gender.FEMALE) ? femaleLastNames : maleLastNames;

            return selectedLastNames[new Random().nextInt(selectedLastNames.length)];
        }
    }
}
