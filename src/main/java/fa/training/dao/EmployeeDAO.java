package fa.training.dao;

import fa.training.dto.EmployeeDTO;
import fa.training.dto.Pagination;
import fa.training.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    Pagination getAllEmployees(int page, int size,String name,String selectOption);
    Employee getEmployeeById(int id);
    void saveEmployee(Employee employee);

    void deleteEmployeeById(int employeeId);
}
