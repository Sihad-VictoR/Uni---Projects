package sample;

import org.junit.Assert;
import org.junit.Test;

public class Over60MemberTest {
    @Test
    public void testGetterSetterAge() {
        final int age = 80;
        final Over60Member over60Member = new Over60Member("","","","",age);
        over60Member.setAge(80);
        Assert.assertEquals(over60Member.getAge(),age );
    }
}