package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class EmployeeDto extends EntityDto {

    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthday;

    public EmployeeDto() {
        super();
    }

    public EmployeeDto(Long id, String firstName, String secondName, String middleName, Date birthday) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(secondName);
        sb.append(" ");
        sb.append(middleName);
        return sb.toString();
    }

}
