package imd.ufrn.familyroutine.model.api;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import imd.ufrn.familyroutine.model.ActivityState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

public class ActivityRequest {
    @NotEmpty
    private String name;
    @NotNull
    private Date dateStart;
    @NotNull
    private Date dateEnd;
    @NotNull
    private Time hourStart;
    @NotNull
    private Time hourEnd;
    @NotNull
    private Long dependentId;
    @NotNull
    private Long currentGuardian;
    @NotNull
    private Long actor;
    @NotNull
    private Long createdBy;
    @NotNull
    private Boolean repeat;
    
    private List<Integer> daysToRepeat = new ArrayList<>();
    private Date repeatUntil;
    
    private String description;
    private ActivityState state = ActivityState.CREATED;

    public ActivityRequest() {
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
    public Boolean isRepeat() {
        return repeat;
    }
    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }
    public List<Integer> getDaysToRepeat() {
        return daysToRepeat;
    }
    public void setDaysToRepeat(List<Integer> daysToRepeat) {
        this.daysToRepeat = daysToRepeat;
    }
    public Date getRepeatUntil() {
        return repeatUntil;
    }
    public void setRepeatUntil(Date repeatUntil) {
        this.repeatUntil = repeatUntil;
    }
}
