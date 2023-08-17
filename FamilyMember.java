
public class FamilyMember extends Person {
    private String id;
    private String fatherId;
    private String motherId;
    private String geneticCode;
    private int childNumber;
    private boolean married;

    private int generation;
    private Gender gender;

    public FamilyMember(String id, String fatherId, String motherId, String geneticCode, int childNumber, Gender gender, String name, String lastName) {
        super(name, lastName);
        this.id = id;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.geneticCode = geneticCode;
        this.childNumber = childNumber;
        this.gender = gender;
        this.married = false;
        this.generation = 0;
    }

    public Gender getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public int getChildNumber() {
        return childNumber;
    }


    public String getFatherId() {
        return fatherId;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getGeneticCode() {
        return geneticCode;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public boolean isMarried() {
        return married;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }




    @Override
    public String toString() {
        return '\n' +
                "Id= " + id + '\n' +
                "FatherId= " + fatherId + '\n' +
                "MotherId= " + motherId + '\n' +
                "GeneticCode= " + geneticCode + '\n' +
                "ChildNumber=" + childNumber +'\n' +
                "Married=" + married +'\n' +
                "Gender=" + gender +'\n' +
                "Name= " + getName() + '\n' +
                "LastName= " + getLastName() + '\n' +
                "Generation= " + getGeneration() + '\n' +
                '\n';
    }


}
