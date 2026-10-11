package com.compile.compile.Service;

import com.compile.compile.Model.Employee;
import com.compile.compile.Repository.EmployeeRepository;
import com.compile.compile.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));
    }

    public Employee update(Long id, Employee request) {
        Employee employee = getById(id);

        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}