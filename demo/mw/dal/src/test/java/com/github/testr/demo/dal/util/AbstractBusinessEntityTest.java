package com.github.testr.demo.dal.util;

import org.apache.commons.lang3.SerializationUtils;
import org.testng.annotations.Test;

import java.io.Serializable;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@SuppressWarnings({"unchecked", "ObjectEqualsNull"})
public class AbstractBusinessEntityTest {

    @Test
    public void testNullBusinessKeys_A() {
        DummyEntityA v1 = new DummyEntityA();
        DummyEntityA v2 = new DummyEntityA();
        assertThat(v1.toString()).isEqualTo("DummyEntityA[*id=<null>]");
        assertThat(v1).isEqualTo(v2);
        assertThat(v1.hashCode()).isEqualTo(v2.hashCode());
    }

    @Test
    public void testNullBusinessKeys_B() {
        DummyEntityB v1 = new DummyEntityB();
        DummyEntityB v2 = new DummyEntityB();
        assertThat(v1.toString()).isEqualTo("DummyEntityB[*id=<null>]");
        assertThat(v1).isEqualTo(v2);
        assertThat(v1.hashCode()).isEqualTo(v2.hashCode());
    }

    @Test
    public void testEntityC() {
        DummyEntityC v1 = new DummyEntityC();
        setField(v1, "id", 1L);
        v1.setEmail("scott@tiger.net");
        v1.setFirstName("Scott");
        v1.setLastName("Tiger");
        assertThat(v1.toString()).isEqualTo("DummyEntityC[id=1,*email=scott@tiger.net,lastName=Tiger]");
        assertThat(deepClone(v1).toString()).isEqualTo("DummyEntityC[id=1,*email=scott@tiger.net,lastName=Tiger]");
        assertResistantAgainstNull(v1);
        assertReflexive(v1);

        // Same business key
        DummyEntityC v2 = new DummyEntityC();
        setField(v2, "id", 2L);
        v2.setEmail("scott@tiger.net");
        v2.setFirstName("Scott2");
        v2.setLastName("Tiger2");
        assertThat(v2.toString()).isEqualTo("DummyEntityC[id=2,*email=scott@tiger.net,lastName=Tiger2]");
        assertThat(deepClone(v2).toString()).isEqualTo("DummyEntityC[id=2,*email=scott@tiger.net,lastName=Tiger2]");
        assertResistantAgainstNull(v1);
        assertReflexive(v1);
        assertEqualsHashCodeConsistent(v1, v2, true, true);

        // Change business key
        v2.setEmail("scott2@tiger.net");
        assertThat(v2.toString()).isEqualTo("DummyEntityC[id=2,*email=scott2@tiger.net,lastName=Tiger2]");
        assertThat(v1).isNotEqualTo(v2);
        assertResistantAgainstNull(v1);
        assertReflexive(v1);
        assertEqualsHashCodeConsistent(v1, v2, false, true);

        DummyEntityC1 v3 = new DummyEntityC1();
        setField(v3, "id", 1L);
        v3.setEmail("scott@tiger.net");
        v3.setFirstName("Scott");
        v3.setLastName("Tiger");
        assertThat(v3.toString()).isEqualTo("DummyEntityC1[id=1,*firstName=Scott,*lastName=Tiger,email=scott@tiger.net]");
        assertResistantAgainstNull(v3);
        assertReflexive(v3);
        // field values should match
        assertThat(v1).isEqualsToByComparingFields(v3);
        // even though (v2 instanceof DummyEntityC)==true and all properties match,
        // business key comparison is always false because DummyEntityC and DummyEntityC1
        // declare different business keys.
        assertEqualsHashCodeConsistent(v1, v3, false, true);
    }

    @Test
    public void testEntityD() {

        DummyEntityC student = new DummyEntityC();
        setField(student, "id", 1L);
        student.setEmail("scott@tiger.net");
        student.setFirstName("Scott");
        student.setLastName("Tiger");
        assertThat(student.toString()).isEqualTo("DummyEntityC[id=1,*email=scott@tiger.net,lastName=Tiger]");

        DummyEntityD school = new DummyEntityD();
        setField(school, "id", 1L);
        school.setCode("S001");
        school.setStudent(student);

        System.out.println(school);
        assertThat(school.toString()).isEqualTo("DummyEntityD[id=1,*code=S001,student=DummyEntityC[id=1,*email=scott@tiger.net,lastName=Tiger]]");
        assertThat(deepClone(school).toString()).isEqualTo("DummyEntityD[id=1,*code=S001,student=DummyEntityC[id=1,*email=scott@tiger.net,lastName=Tiger]]");
        assertResistantAgainstNull(school);
        assertReflexive(school);


    }

    private static void assertReflexive(Serializable a) {
        assertThat(a.equals(a)).isTrue();
        assertThat(a.equals(deepClone(a))).isTrue();
        assertThat(deepClone(a).equals(a)).isTrue();
        assertThat(deepClone(a).equals(deepClone(a))).isTrue();
    }

    private static void assertResistantAgainstNull(Serializable a) {
        assertThat(a.equals(null)).isFalse();
        assertThat(deepClone(a).equals(null)).isFalse();
    }

    private static void assertEqualsHashCodeConsistent(Serializable a,
                                                       Serializable b,
                                                       boolean isExpectedEqual,
                                                       boolean notEqualGivesDifferentHash) {
        assertThat(a.equals(b)).isEqualTo(isExpectedEqual);
        assertThat(deepClone(a).equals(b)).isEqualTo(isExpectedEqual);

        assertThat(b.equals(a)).isEqualTo(isExpectedEqual);
        assertThat(deepClone(b).equals(a)).isEqualTo(isExpectedEqual);

        assertThat(deepClone(a).equals(deepClone(b))).isEqualTo(isExpectedEqual);
        assertThat(deepClone(b).equals(deepClone(a))).isEqualTo(isExpectedEqual);

        if (isExpectedEqual) {
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
            assertThat(deepClone(a).hashCode()).isEqualTo(b.hashCode());
        } else if (notEqualGivesDifferentHash) {
            // This is valid only for specific cases as it's not guaranteed
            // that a.eq(b)==false ==>  a.hashCode()!=b.hashCode()
            // so this is a best effort to check the absolute minimal quality
            // of the hash algorithm
            assertThat(a.hashCode()).isNotEqualTo(b.hashCode());
            assertThat(deepClone(a).hashCode()).isNotEqualTo(b.hashCode());
        }
    }

    private static <T extends Serializable> T deepClone(T v) {
        return (T) SerializationUtils.deserialize(SerializationUtils.serialize(v));
    }

}
