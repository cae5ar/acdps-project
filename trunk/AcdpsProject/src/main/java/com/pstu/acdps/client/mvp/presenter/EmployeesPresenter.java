package com.pstu.acdps.client.mvp.presenter;

import com.pstu.acdps.server.dao.EmployeeDto;

public interface EmployeesPresenter {
    public void saveEmployee(EmployeeDto bean);
    public void deleteEmployee(Long id);
    public void getAllEmployyes();
}
