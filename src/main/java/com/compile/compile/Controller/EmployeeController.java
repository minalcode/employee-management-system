package com.compile.compile.Controller;

import com.compile.compile.Model.Employee;
import com.compile.compile.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> create(
            @RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return ResponseEntity.ok(service.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}