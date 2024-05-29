package app.dto;

import app.entity.Team;

public class TeamJsonDTO {
    public Long id;
    public String name;
    public String creationDate;
    public String city;
    public Double budget;

    public TeamJsonDTO(){}

    public TeamJsonDTO(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.creationDate = team.getCreationDate().toString();
        this.city = team.getCity();
    }





}
