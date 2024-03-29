package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class JobPosDto extends EntityDto{

    private Long job = null;
    private Long departmentId = null;
    private EmployeeDto employeeDto = null;
    private Date startDate;
    private Date endDate;
    /**
     * поле необязательное, нужно чтоб не обращатся отдельно за подразделением если нужно только имя
     */
    private String departmentName = null;

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
    
    /**
     * поле необязательное, нужно чтоб не обращатся отдельно за подразделением если нужно только имя
     */
    public String getDepartmentName() {
        return departmentName;
    }
    
    /**
     * поле необязательное, нужно чтоб не обращатся отдельно за подразделением если нужно только имя
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
