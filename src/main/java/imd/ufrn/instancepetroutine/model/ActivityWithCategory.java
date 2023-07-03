package imd.ufrn.instancepetroutine.model;

import imd.ufrn.framework.model.ActivityAbstract;

public class ActivityWithCategory extends ActivityAbstract {

    private Long categoryId;

    public ActivityWithCategory() {

    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }    
}
