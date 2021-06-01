package sample;

import org.junit.Assert;
import org.junit.Test;

public class DefaultMemberTest {
    DefaultMember defaultMember = new DefaultMember("","","","");
    @Test
    public void testMembershipID() {
        final String id= "GMEM64";
        defaultMember.setMembershipId(id);
        Assert.assertEquals(defaultMember.getMembershipId(),id );
    }
    @Test
    public void testFirstName() {
        final String fname= "Sihad";
        defaultMember.setFirstName(fname);
        Assert.assertEquals(defaultMember.getFirstName(),fname );
    }
    @Test
    public void testLastName() {
        final String lName= "Fareed";
        defaultMember.setLastName(lName);
        Assert.assertEquals(defaultMember.getLastName(),lName );
    }
    @Test
    public void testMemberStartDate() {
        final String date= "12/07/2000";
        defaultMember.setMembershipStartDate(date);
        Assert.assertEquals(defaultMember.getMembershipStartDate(),date );
    }
}