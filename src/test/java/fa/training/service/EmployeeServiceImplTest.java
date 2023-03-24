package fa.training.service;

import fa.training.dto.Pagination;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmployeeServiceImplTest {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployees(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllEmployees() {
        // TODO: Complete this test.
        //   Reason: R021 Missing Spring component.
        //   Diffblue Cover detected Spring on the classpath, but failed to load critical
        //   components while building the Spring context.
        //   Make sure these components can be loaded.
        //   See https://diff.blue/R021 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        int page = 0;
        int size = 0;
        String name = "";
        String selectOption = "";

        // Act
        Pagination actualAllEmployees = this.employeeServiceImpl.getAllEmployees(page, size, name, selectOption);

        // Assert
        // TODO: Add assertions on result
    }
}

