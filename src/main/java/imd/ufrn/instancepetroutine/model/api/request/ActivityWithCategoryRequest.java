package imd.ufrn.instancepetroutine.model.api.request;

import imd.ufrn.framework.model.api.request.ActivityRequest;

public class ActivityWithCategoryRequest extends ActivityRequest {

    private Long categoryId;

    public ActivityWithCategoryRequest() {
        
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
