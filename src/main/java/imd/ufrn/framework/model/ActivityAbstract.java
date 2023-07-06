package imd.ufrn.framework.model;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.annotation.Id;

public abstract class ActivityAbstract {
    @Id
    private Long id;
    private String name;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private Time hourStart;
    private Time hourEnd;
    private ActivityState state;
    private String commentary;

    private Long actor;
    
    private Long dependentId;

    private Long recurringActivityId;

    private Long currentUser;
    private Long createdBy;
    private Long finishedBy;
    
    public ActivityAbstract() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public ActivityState getState() {
        return state;
    }
    public void setState(ActivityState state) {
        this.state = state;
    }
    public String getCommentary() {
        return commentary;
    }
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
    public Long getDependentId() {
        return dependentId;
    }
    public void setDependentId(Long dependentId) {
        this.dependentId = dependentId;
    }
    public Long getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(Long currentUser) {
        this.currentUser = currentUser;
    }
    public Long getActor() {
        return actor;
    }
    public void setActor(Long actor) {
        this.actor = actor;
    }
    public Long getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Long getFinishedBy() {
        return finishedBy;
    }
    public void setFinishedBy(Long finishedBy) {
        this.finishedBy = finishedBy;
    }
    public Long getRecurringActivityId() {
        return recurringActivityId;
    }
    public void setRecurringActivityId(Long recurringActivityId) {
        this.recurringActivityId = recurringActivityId;
    }
}