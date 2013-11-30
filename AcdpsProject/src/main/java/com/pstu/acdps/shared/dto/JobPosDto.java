package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class JobPosDto extends EntityDto{

    private Long departmentId = null;
    private Long jobId = null;
    private EmployeeDto employeeDto = null;
    private Date startDate;
    private Date endDate;
    
    public JobPosDto() {
        super();
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
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
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
    
    
}
