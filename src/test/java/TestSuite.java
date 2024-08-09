import org.example.CatalogAppWeb2.Controller.GradeControllerTest;
import org.example.CatalogAppWeb2.Controller.StudentControllerTest;
import org.example.CatalogAppWeb2.Controller.SubjectControllerTest;
import org.example.CatalogAppWeb2.Service.StudentServiceTest;
import org.example.CatalogAppWeb2.Service.SubjectServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({GradeControllerTest.class, SubjectControllerTest.class, StudentControllerTest.class,
        SubjectServiceTest.class, StudentServiceTest.class})
public class TestSuite {
}
