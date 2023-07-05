package imd.ufrn.framework.model.api.request;

import jakarta.validation.constraints.NotNull;

public abstract class FinishActivityRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Boolean done;
    private String commentary;

    public FinishActivityRequest() {
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
