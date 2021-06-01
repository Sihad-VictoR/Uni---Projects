package sample;

import org.junit.Assert;
import org.junit.Test;

public class StudentMemberTest {

    @Test
    public void testSchoolName() {
        final String schoolName = "School Name";
        final StudentMember studentMember = new StudentMember("","","","",schoolName);
        studentMember.setSchoolName(schoolName);
        Assert.assertEquals(studentMember.getSchoolName(),schoolName );
    }
}