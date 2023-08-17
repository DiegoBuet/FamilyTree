import java.util.Random;

public class NameGenerator {

    public static String generateRandomName(Gender gender) {

        if (gender == Gender.MALE) {
            String[] maleNames = {
                    "Juan", "Pedro", "Carlos", "Santiago", "Mateo", "Matías", "Daniel", "David", "Sebastián", "Diego",
                    "Alejandro", "Andrés", "José", "Luis", "Gabriel", "Lucas", "Leonardo", "Felipe", "Fernando", "Ricardo",
                    "Nicolás", "Manuel", "Rodrigo", "Cristian", "Hugo", "Martín", "Alberto", "Ramón", "Jorge", "Tomás",
                    "Salvador", "Oscar", "Sergio", "Antonio", "Julián", "Ignacio", "Mauricio", "Esteban", "Rubén", "Francisco",
                    "Hernán", "Rafael", "Miguel", "Guillermo", "Eduardo", "Emilio", "Marco"
            };
            return maleNames[new Random().nextInt(maleNames.length)];
        } else {
            String[] femaleNames = {
                    "María", "Ana", "Laura", "Sofía", "Valentina", "Isabella", "Camila", "Valeria", "Victoria", "Lucía",
                    "Natalia", "Alejandra", "Gabriela", "Paula", "Juliana", "Fernanda", "Carolina", "Daniela", "Sara", "Andrea",
                    "Catalina", "Isabel", "Clara", "Elena", "Emilia", "Antonia", "Martina", "Juana", "Adriana", "Beatriz",
                    "Clara", "Estefanía", "Josefina", "Mariana", "Regina", "Rosa", "Lorena", "Marina", "Silvia", "Patricia",
                    "Vanessa", "Verónica", "Raquel", "Noelia", "Mónica", "Sandra", "Patricia", "Leonor", "Olivia", "Julia"
            };
            return femaleNames[new Random().nextInt(femaleNames.length)];
        }
    }
}
