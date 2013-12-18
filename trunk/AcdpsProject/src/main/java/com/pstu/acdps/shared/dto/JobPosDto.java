package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class JobPosDto extends EntityDto{

    private Long job = null;
    private Long departmentId = null;
    private String departmentName = null;
    private EmployeeDto employeeDto = null;
    private Date startDate;
    private Date endDate;
    
    public JobPosDto() {
        super();
        employeeDto = new EmployeeDto();
    }
    
    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }


    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getJob() {
        return job;
    }

    public void setJob(Long job) {
        this.job = job;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
