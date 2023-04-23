package imd.ufrn.familyroutine.model.api.request;

import jakarta.validation.constraints.NotNull;

public class FinishActivityRequest {
    @NotNull
    private Long guardianId;
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
}
