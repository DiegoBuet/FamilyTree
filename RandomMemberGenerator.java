import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class RandomMemberGenerator {
    private Map<String, FamilyMember> generatedMembers;

    public RandomMemberGenerator() {
        generatedMembers = new HashMap<>();
    }

    public List<FamilyMember> generateRandomMembers(int count) {
        List<FamilyMember> newMembers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            FamilyMember member = createRandomMember();
            newMembers.add(member);
            generatedMembers.put(member.getId(), member);
        }

        return newMembers;
    }

    public FamilyMember getGeneratedMemberById(String memberId) {
        return generatedMembers.get(memberId);
    }

    private FamilyMember createRandomMember() {
        String fatherId = generateRandomId();
        String motherId = generateRandomId();
        int childNumber = 1; // Start with 1 for the first child
        String id = applyRulesToParentIds(fatherId, motherId, childNumber);
        Gender memberGender = generateRandomGender();

        String name = NameGenerator.generateRandomName(memberGender);
        String lastName = LastNameGenerator.generateRandomLastName(memberGender, false); // Assuming not a couple

        return new FamilyMember(id, fatherId, motherId, applyRulesToGeneticCode(fatherId, childNumber, motherId),
                childNumber, memberGender, name, lastName);
    }

    private String applyRulesToGeneticCode(String fatherId, int childNumber, String motherId) {
        return fatherId + "-" + childNumber + "-" + motherId;
    }

    private Gender generateRandomGender() {
        Random random = new Random();
        int genderIndex = random.nextInt(3);

        if (genderIndex == 0) {
            return Gender.MALE;
        } else if (genderIndex == 1) {
            return Gender.FEMALE;
        } else {
            return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        }
    }

    private String applyRulesToParentIds(String fatherId, String motherId, int childNumber) {
        char firstLetterFather = fatherId.charAt(0);
        char lastLetterFather = fatherId.charAt(fatherId.length() - 1);
        char firstLetterMother = motherId.charAt(0);
        char lastLetterMother = motherId.charAt(motherId.length() - 1);

        return String.valueOf(firstLetterFather) + String.valueOf(lastLetterFather) +
                String.valueOf(firstLetterMother) + String.valueOf(lastLetterMother) +
                "-" + childNumber;
    }

    protected String generateRandomId() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder idBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char randomLetter = letters.charAt(random.nextInt(letters.length()));
            idBuilder.append(randomLetter);
        }
        return idBuilder.toString();
    }
}
