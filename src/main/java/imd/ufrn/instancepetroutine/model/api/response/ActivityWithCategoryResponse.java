package imd.ufrn.instancepetroutine.model.api.response;

import imd.ufrn.framework.model.api.response.ActivityResponse;

public class ActivityWithCategoryResponse extends ActivityResponse {

    private Long categoryId;
    private String categoryName;

    public ActivityWithCategoryResponse() {
        
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
