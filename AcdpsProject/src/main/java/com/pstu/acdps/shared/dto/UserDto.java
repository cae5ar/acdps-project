package com.pstu.acdps.shared.dto;

@SuppressWarnings("serial")
public class UserDto extends EntityDto {

    private String name = null;
    private String login = null;
    
    public UserDto() {
        super();
    }

    public UserDto(Long id) {
        super(id);
    }
    
    public UserDto(Long id, String name, String login) {
        super(id);
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
