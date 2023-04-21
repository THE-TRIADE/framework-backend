package imd.ufrn.familyroutine.model.api;

import java.sql.Date;
import java.sql.Time; 

public class ActivityResponse {
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time hourStart;
    private Time hourEnd;
    
    private Long dependentId;

    private Long currentGuardian;
    private String currentGuardianEmail;
    private String currentGuardianName;

    private Long actor;
    private String actorName;

    private Long createdBy;
    private Long createdByEmail;
    private Long createdByName;

    private Boolean repeat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Time getHourStart() {
        return hourStart;
    }

    public void setHourStart(Time hourStart) {
        this.hourStart = hourStart;
    }

    public Time getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Time hourEnd) {
        this.hourEnd = hourEnd;
    }

    public Long getDependentId() {
        return dependentId;
    }

    public void setDependentId(Long dependentId) {
        this.dependentId = dependentId;
    }

    public Long getCurrentGuardian() {
        return currentGuardian;
    }

    public void setCurrentGuardian(Long currentGuardian) {
        this.currentGuardian = currentGuardian;
    }

    public String getCurrentGuardianEmail() {
        return currentGuardianEmail;
    }

    public void setCurrentGuardianEmail(String currentGuardianEmail) {
        this.currentGuardianEmail = currentGuardianEmail;
    }

    public String getCurrentGuardianName() {
        return currentGuardianName;
    }

    public void setCurrentGuardianName(String currentGuardianName) {
        this.currentGuardianName = currentGuardianName;
    }

    public Long getActor() {
        return actor;
    }

    public void setActor(Long actor) {
        this.actor = actor;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(Long createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public Long getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(Long createdByName) {
        this.createdByName = createdByName;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }
}
