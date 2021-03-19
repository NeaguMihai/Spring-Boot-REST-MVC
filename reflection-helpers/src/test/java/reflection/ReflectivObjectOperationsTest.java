package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static reflection.ReflectivObjectOperations.fillFields;
import static reflection.ReflectivObjectOperations.getNonNullFields;

class ReflectivObjectOperationsTest {

    @Test
    void getNonNullFieldsTest() {
        TestPOJO testPOJO = new TestPOJO();

        testPOJO.setA(2);
        assertEquals(1,getNonNullFields(testPOJO).size());

        testPOJO.setB("aa");
        assertEquals(2,getNonNullFields(testPOJO).size());

        testPOJO.setC(new Date());
        assertEquals(3,getNonNullFields(testPOJO).size());
    }

    @Test
    void fillFieldsTest() {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setA(1);
        testPOJO.setB("aa");
        testPOJO.setC(new Date());

        TestPOJO finalPOJO = new TestPOJO();

        assertNotEquals(testPOJO.getA(), finalPOJO.getA());
        assertNotEquals(testPOJO.getB(), finalPOJO.getB());
        assertNotEquals(testPOJO.getC(), finalPOJO.getC());

        fillFields(testPOJO, finalPOJO);

        assertEquals(testPOJO.getA(), testPOJO.getA());
        assertEquals(testPOJO.getB(), testPOJO.getB());
        assertEquals(testPOJO.getC(), testPOJO.getC());
    }
}