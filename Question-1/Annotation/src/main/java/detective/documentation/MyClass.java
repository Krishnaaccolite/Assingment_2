package detective.documentation;
@ClassDocumentation("Sample class.")
public class MyClass {
    public void myMethod() {
        //Dummy method
    }
    @MethodDocumentation("Sample task.")
    public String mainMethod(int input) {
        return ("Output is " + input);
    }
}
