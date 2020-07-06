package next.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @DisplayName("Requirement - 1 : 클래스 정보 출력 (Fields)")
    @Test
    public void showClass_Fields() {
        //given
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        //when
        Field[] declaredFields = clazz.getDeclaredFields();

        //then
        assertThat(declaredFields.length).isEqualTo(6);
        IntStream.range(0, declaredFields.length)
                .forEach(i -> System.out.println("Field : " + declaredFields[i]));
    }

    @DisplayName("Requirement - 1 : 클래스 정보 출력 (Constructors)")
    @Test
    void showClass_Constructors() {
        //given
        Class<Question> clazz = Question.class;

        //when
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        //then
        assertThat(declaredConstructors.length).isEqualTo(2);
        IntStream.range(0, declaredConstructors.length)
                .forEach(i -> System.out.println("Constructor: " + declaredConstructors[i]));
    }

    @DisplayName("Requirement - 1 : 클래스 정보 출력 (Methods)")
    @Test
    void showClass_Methods() {
        //given
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        //when
        Method[] declaredMethods = clazz.getDeclaredMethods();

        //then
        assertThat(declaredMethods.length).isEqualTo(11);
        IntStream.range(0, declaredMethods.length)
                .forEach(i -> logger.debug(String.valueOf(declaredMethods[i])));
    }

    @DisplayName("Requirement - 4 : private field에 값 할당")
    @Test
    void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        //given
        Class<Student> clazz = Student.class;
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        Student student = new Student();
        assertThat(student.getName()).isNull();

        //when
        name.set(student, "Lee Seunghee");

        //then
        assertThat(student.getName()).isEqualTo("Lee Seunghee");
    }

    @DisplayName("Requirement - 5 : 인자를 가진 생성자의 인스턴스 생성")
    @Test
    void createInstanceWithArgs() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        //given
        Class<Question> clazz = Question.class;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        //when
        Question question = (Question) declaredConstructors[0].newInstance("Seunghee", "Reflection", "Contents");

        //then
        assertThat(question.getTitle()).isEqualTo("Reflection");
        assertThat(question.getWriter()).isEqualTo("Seunghee");
        assertThat(question.getContents()).isEqualTo("Contents");
    }
}
