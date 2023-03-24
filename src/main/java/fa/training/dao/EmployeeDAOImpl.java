package fa.training.dao;

import fa.training.dto.EmployeeDTO;
import fa.training.dto.Pagination;
import fa.training.entities.Account;
import fa.training.entities.Employee;
import fa.training.utils.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {


    //need to injection session factory
    @Autowired
    private SessionFactory sessionFactory;
    private String  createQueryString(String name, String selectOption){
        String query=null;
        if("".equals(name)&&"".equals(selectOption)){
            query="select employeeId,firstName,lastName,dateOfBirth,phone,address,departmentName " +
                    "from Employee";
        }else{
             if("name".equals(selectOption)){
                query="select employeeId,firstName,lastName,dateOfBirth,phone,address,departmentName from " +
                        "Employee where firstName IS NULL OR firstName  like '%"+name+"%' " +
                        "OR lastName IS NULL OR lastName like '%"+name+"%' order by employeeId";
            }else{
                 query="select employeeId,firstName,lastName,dateOfBirth,phone,address,departmentName " +
                         "from Employee where "+selectOption+" IS NULL OR" +
                         " " +selectOption+"  like '%"+name+"%' order by employeeId";
             }
        }
        return query;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination getAllEmployees(int page, int size,String name,String selectOption) {
        try{
            Pagination pagination = new Pagination();
            Session session= sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Object[]> listFiter= session.createQuery(createQueryString(name,selectOption)).list();
            pagination.setTotalRecords(listFiter.size());
            pagination.setTotalPage((int) (Math.ceil(listFiter.size() / size)));
            Query theQuery =
                    session.createQuery(
                            createQueryString(name,selectOption));
            theQuery.setFirstResult((page - 1) * size);
            theQuery.setMaxResults(size);
            List<Object[]> objects =  theQuery.list();
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            Util.mapper(employeeDTOS,objects);
            pagination.setData(employeeDTOS);
            session.getTransaction().commit();
            return pagination;
        }catch (Exception e){
            return  new Pagination();
        }
    }







    @Override
    public Employee getEmployeeById(int id) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            //get session by Id
            Query<Employee> theQuery =currentSession.createQuery("from Employee where employeeId =:employeeId", Employee.class);
            theQuery.setParameter("employeeId",id);
            Employee employee=theQuery.getSingleResult();
            currentSession.getTransaction().commit();
            return employee;
        }catch (Exception e){
            return new Employee();
        }
    }


    @Override
    public void saveEmployee(Employee employee) {
        try(Session currentSession = sessionFactory.openSession()){
            currentSession.beginTransaction();
            employee.getAccount().setEmployee(employee);
            currentSession.saveOrUpdate(employee);
            currentSession.getTransaction().commit();
        }
    }
    @Override
    public void deleteEmployeeById(int employeeId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee =  getEmployeeById(employeeId);
            session.delete(employee);
            session.getTransaction().commit();
        }
    }
}
