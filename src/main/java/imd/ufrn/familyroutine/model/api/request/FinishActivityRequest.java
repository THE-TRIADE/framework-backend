package imd.ufrn.familyroutine.model.api.request;

import jakarta.validation.constraints.NotNull;

public class FinishActivityRequest {
    @NotNull
    private Long guardianId;
    @NotNull
    private Boolean done;
    private String commentary;

    public FinishActivityRequest() {
    }

    public Long getGuardianId() {
        return guardianId;
    }
    public void setGuardianId(Long guardianId) {
        this.guardianId = guardianId;
    }
    public String getCommentary() {
        return commentary;
    }
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
    public Boolean getDone() {
        return done;
    }
    public void setDone(Boolean done) {
        this.done = done;
    }
}
