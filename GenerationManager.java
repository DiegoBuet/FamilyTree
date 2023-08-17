import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class GenerationManager {

    private List<FamilyMember> currentGeneration;
    private int generation = 1;

    private Map<Integer, List<FamilyMember>> generationsMap;
    private RandomMemberGenerator randomMemberGenerator;
    private Map<Integer, List<FamilyMember>> childrenMap;

    private Map<Integer, List<FamilyMember>> currentGenerationMap;

    private Map<Integer, Integer> childCountMap;


    public GenerationManager(RandomMemberGenerator randomMemberGenerator) {
        this.randomMemberGenerator = randomMemberGenerator;
        this.generationsMap = new HashMap<>();
        this.childrenMap = new HashMap<>();
        this.currentGenerationMap = new HashMap<>();
        this.childCountMap = new HashMap<>();
    }

    public List<FamilyMember> generateGenerations(int numGenerations) {
        List<FamilyMember> generatedMembers = new ArrayList<>();

        for (int i = 1; i <= numGenerations; i++) {
            List<FamilyMember> randomMembers = randomMemberGenerator.generateRandomMembers(i == 1 ? 4 : 5);

            if (i == 1) {
                generationsMap.put(i, createFirstGeneration(randomMembers));
            } else {
                currentGeneration = generationsMap.get(i - 1);
                generationsMap.put(i, createGeneration(currentGeneration, randomMembers, i));
            }

            generatedMembers.addAll(generationsMap.get(i));
            List<FamilyMember> childrenForNextGeneration = childrenMap.get(i);
            if (childrenForNextGeneration != null) {
                generationsMap.get(i).addAll(childrenForNextGeneration);
                childrenMap.remove(i);
            }

            // Actualizar el mapa de la generación actual con la información actual
            currentGenerationMap.put(i, new ArrayList<>(currentGeneration));

            // Imprimir la información generada para la generación actual
            printGeneratedDataForGeneration(i);
        }

        return generatedMembers;
    }

    private List<FamilyMember> createFirstGeneration(List<FamilyMember> randomMembers) {
        List<FamilyMember> firstGeneration = new ArrayList<>();
        List<FamilyMember> pairs = formPairs(randomMembers);

        FamilyMember male = pairs.get(0);
        FamilyMember female = pairs.get(1);
        int childNumber = 1;

        String childId = applyRulesToParents(male, female, childNumber);
        Gender childGender = generateRandomGender();
        String childName = NameGenerator.generateRandomName(childGender);
        String childLastName = male.getLastName();

        String geneticCode = applyRulesToGeneticCode(male.getId(), childNumber, female.getId());

        FamilyMember child = new FamilyMember(childId, male.getId(), female.getId(),
                geneticCode, childNumber, childGender, childName, childLastName);

        child.setMarried(false);
        child.setGeneration(generation + 1);

        firstGeneration.add(male);
        firstGeneration.add(female);
        firstGeneration.add(child);

        List<FamilyMember> childrenForFirstGeneration = new ArrayList<>();
        childrenForFirstGeneration.add(child);
        childrenMap.put(generation + 1, childrenForFirstGeneration);

        currentGeneration = firstGeneration;
        return firstGeneration;
    }



    private List<FamilyMember> createGeneration(List<FamilyMember> previousGeneration, List<FamilyMember> randomMembers, int generationNumber) {
        List<FamilyMember> nextGenerationMembers = new ArrayList<>();

        int k = 0;
        int inheritorIndex1 = k;
        int inheritorIndex2 = k + 1;
        k += 2;

        FamilyMember inheritor1 = previousGeneration.get(inheritorIndex1);
        FamilyMember inheritor2 = previousGeneration.get(inheritorIndex2);

        List<FamilyMember> pairs = formPairs(randomMembers);

        List<FamilyMember> children = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i += 2) {
            FamilyMember male = pairs.get(i);
            FamilyMember female = pairs.get(i + 1);

            FamilyMember child = createChildForNextGeneration(inheritor1, inheritor2, generationNumber + 1, male, female);
            children.add(child);
        }
        childrenMap.put(generationNumber + 1, children);

        // Generar miembros aleatorios y asignarles la generación correcta
        List<FamilyMember> randomMembersWithGeneration = new ArrayList<>();
        for (FamilyMember randomMember : randomMembers) {
            FamilyMember memberWithGeneration = new FamilyMember(
                    randomMember.getId(),
                    randomMember.getFatherId(),
                    randomMember.getMotherId(),
                    randomMember.getGeneticCode(),
                    randomMember.getChildNumber(),
                    randomMember.getGender(),
                    randomMember.getName(),
                    randomMember.getLastName()
            );
            memberWithGeneration.setMarried(randomMember.isMarried());
            memberWithGeneration.setGeneration(generationNumber);
            randomMembersWithGeneration.add(memberWithGeneration);
        }

        nextGenerationMembers.addAll(randomMembersWithGeneration); // Agregar los miembros aleatorios
        nextGenerationMembers.addAll(children); // Agregar los hijos generados

        currentGeneration = nextGenerationMembers;

        return nextGenerationMembers;
    }


    private FamilyMember createChildForNextGeneration(FamilyMember father, FamilyMember mother, int generationNumber, FamilyMember male, FamilyMember female) {
        int childCount = childCountMap.getOrDefault(generationNumber, 0) + 1;
        childCountMap.put(generationNumber, childCount);

        String childId = applyRulesToParents(male, female, childCount);
        Gender childGender = generateRandomGender();
        String childName = NameGenerator.generateRandomName(childGender);
        String childLastName = father.getLastName();

        String geneticCode = applyRulesToGeneticCode(male.getId(), childCount, female.getId());

        FamilyMember child = new FamilyMember(childId, male.getId(), female.getId(),
                geneticCode, childCount, childGender, childName, childLastName);

        child.setMarried(false);
        child.setGeneration(generationNumber + 1); // Corregir el número de generación

        List<FamilyMember> childrenForCurrentGeneration = childrenMap.getOrDefault(generationNumber + 1, new ArrayList<>());
        childrenForCurrentGeneration.add(child);
        childrenMap.put(generationNumber + 1, childrenForCurrentGeneration);

        currentGeneration.add(child);

        return child;
    }


    private String applyRulesToParents(FamilyMember father, FamilyMember mother, int childNumber) {
        String fatherId = father.getId();
        String motherId = mother.getId();
        fatherId = fatherId.substring(0, 4);
        motherId = motherId.substring(0, 4);

        char firstLetterFather = fatherId.charAt(0);
        char lastLetterFather = fatherId.charAt(fatherId.length() - 1);
        char firstLetterMother = motherId.charAt(0);
        char lastLetterMother = motherId.charAt(motherId.length() - 1);

        String lastName = father.getLastName();

        int newChildNumber = childNumber != 1 ? childNumber + 1 : 0;

        return String.valueOf(firstLetterFather) + String.valueOf(lastLetterFather) +
                String.valueOf(firstLetterMother) + String.valueOf(lastLetterMother) +
                "-" + newChildNumber;
    }

    private List<FamilyMember> formPairs(List<FamilyMember> members) {
        List<FamilyMember> pairs = new ArrayList<>();
        List<FamilyMember> males = new ArrayList<>();
        List<FamilyMember> females = new ArrayList<>();

        for (FamilyMember member : members) {
            if (member.getGender() == Gender.MALE) {
                males.add(member);
            } else if (member.getGender() == Gender.FEMALE) {
                females.add(member);
            }
        }

        int numPairs = Math.min(males.size(), females.size());
        System.out.println("Number of Pairs Formed: " + numPairs);

        for (int i = 0; i < numPairs; i++) {
            FamilyMember male = males.get(i);
            FamilyMember female = females.get(i);

            // Establecer los atributos "married" y "generation" para ambos miembros de la pareja
            male.setMarried(true);
            male.setGeneration(generation);
            female.setMarried(true);
            female.setGeneration(generation);

            pairs.add(male);
            pairs.add(female);

            System.out.println("Pair " + (i + 1) + ":");
            System.out.println("Male: ID=" + male.getId() + " | Gender=" + male.getGender() + " | Married=" + male.isMarried() + " | Generation=" + male.getGeneration());
            System.out.println("Female: ID=" + female.getId() + " | Gender=" + female.getGender() + " | Married=" + female.isMarried() + " | Generation=" + female.getGeneration());
        }

        return pairs;
    }



    public List<FamilyMember> generateRandomMembers(int count) {
        return randomMemberGenerator.generateRandomMembers(count);
    }

    public List<FamilyMember> getCurrentGeneration() {
        return currentGeneration;
    }

    private Gender generateRandomGender() {
        Random random = new Random();
        int genderIndex = random.nextInt(2); // Cambiado a 2 para que solo genere 0 o 1

        if (genderIndex == 0) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    private String applyRulesToGeneticCode(String fatherId, int childNumber, String motherId) {
        String fatherPrefix = fatherId.substring(0, 4);
        String motherPrefix = motherId.substring(0, 4);
        return fatherPrefix + "-" + childNumber + "-" + motherPrefix;
    }

    public void printGeneratedDataForGeneration(int generationNumber) {
        List<FamilyMember> generation = currentGenerationMap.get(generationNumber);
        if (generation != null) {
            List<FamilyMember> children = childrenMap.getOrDefault(generationNumber, new ArrayList<>());
            System.out.println("Generation " + generationNumber + ":");

            for (int i = 0; i < generation.size(); i++) {
                FamilyMember member = generation.get(i);
                if (member.getGeneration() == generationNumber) {
                    System.out.println(member);
                }
            }

            System.out.println();
        } else {
            System.out.println("Generation " + generationNumber + " not found.");
        }
    }
}