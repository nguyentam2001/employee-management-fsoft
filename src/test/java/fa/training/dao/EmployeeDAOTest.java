package fa.training.dao;

import fa.training.dto.EmployeeDTO;
import fa.training.dto.Pagination;
import fa.training.entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDAOTest {
    static  EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @BeforeAll
    public static void beforeAll(){
        employeeDAO = new EmployeeDAOImpl();
    }


    @Test
    public void testPagination(){
        Pagination pagination = employeeDAO.getAllEmployees(1, 2,"","");
        assertEquals(2,pagination.getTotalPage());
    }
}
