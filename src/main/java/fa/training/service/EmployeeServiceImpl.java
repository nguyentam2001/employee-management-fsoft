package fa.training.service;

import fa.training.dao.EmployeeDAO;
import fa.training.dto.EmployeeDTO;
import fa.training.dto.Pagination;
import fa.training.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeDAO employeeDAO;


    @Override
    public Pagination getAllEmployees(int page, int size,String name,String selectOption) {
        return employeeDAO.getAllEmployees(page, size,name,selectOption);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }


    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeDAO.deleteEmployeeById(employeeId);
    }
}
