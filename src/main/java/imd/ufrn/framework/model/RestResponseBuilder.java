package imd.ufrn.framework.model;

import org.springframework.http.HttpStatus;

public class RestResponseBuilder {
    private int status;
    private String error;
    private String message;
    private String path;

    public RestResponseBuilder status(int status) {
        this.status = status;
        return this;
    }

    public RestResponseBuilder status(HttpStatus status) {
        this.status = status.value();
        if (status.isError()) {
            this.error = status.getReasonPhrase();
        }
        return this;
    }

    public RestResponseBuilder error(String error) {
        this.error = error;
        return this;
    }

    public RestResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public RestResponseBuilder path(String path) {
        this.path = path;
        return this;
    }

    public RestResponse build() {
        RestResponse response = new RestResponse();
        response.setStatus(status);
        response.setError(error);
        response.setMessage(message);
        response.setPath(path);
        return response;
    }
}
