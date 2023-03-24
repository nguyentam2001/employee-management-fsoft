package fa.training.service;

import fa.training.dto.Pagination;
import fa.training.entities.Employee;


public interface EmployeeService {
    Pagination getAllEmployees(int page, int size,String name,String selectOption);

    Employee getEmployeeById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(int employeeId);
}
