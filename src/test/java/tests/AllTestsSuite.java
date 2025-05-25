package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    AppointmentTest.class,
    AppointmentFullFormTest.class,
    LoginTest.class,
    LogoutTest.class,
    HeaderTest.class,
    HoverTest.class,
    AppointmentRandomFormTest.class,
    MultipleStaticPagesTest.class

})
public class AllTestsSuite {
}
