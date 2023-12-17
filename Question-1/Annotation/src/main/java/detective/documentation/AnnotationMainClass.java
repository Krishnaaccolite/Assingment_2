package detective.documentation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodDocumentation {
    String value() default "Krishna";
}
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface ClassDocumentation {
    String value() default "koli";
}

public class AnnotationMainClass {
    static public void main(String[] args) {
        inspectMethodDoc(MyClass.class);
        inspectClassDoc(MyClass.class);
    }
    static private void inspectMethodDoc(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();
        for(Method method : methods) {
            MethodDocumentation methodDoc = method.getAnnotation(MethodDocumentation.class);
            System.out.println("Method: " + method.getName() + ", " + (methodDoc == null ? " Documentation Not Found" : "Documentation Found " + methodDoc.value()));
        }
    }
    static private void inspectClassDoc(Class<?> cl) {
        ClassDocumentation classDoc = cl.getAnnotation(ClassDocumentation.class);
        System.out.println("Class Documentation: " + (classDoc == null ? "No Class Documentation": classDoc.value()));
    }
}
