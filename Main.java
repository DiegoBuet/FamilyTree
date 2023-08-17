
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomMemberGenerator randomMemberGenerator = new RandomMemberGenerator();
        GenerationManager gmAux = new GenerationManager(randomMemberGenerator);

        int numGenerations = 7;
        List<FamilyMember> generatedMembers = gmAux.generateGenerations(numGenerations);

        for (int generation = 1; generation <= numGenerations; generation++) {
            gmAux.printGeneratedDataForGeneration(generation);
        }
    }
}