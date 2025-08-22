package com.ofb.lib.handlers.exception.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResponseErrorTemplate {

    @Valid
    private List<@Valid ResponseErrorsInnerTemplate> errors = new ArrayList<>();

    private @Nullable MetaErrorResponseTemplate meta;

    public ResponseErrorTemplate() {
        super();
    }

    public ResponseErrorTemplate(List<@Valid ResponseErrorsInnerTemplate> errors) {
        this.errors = errors;
    }

    public ResponseErrorTemplate(List<@Valid ResponseErrorsInnerTemplate> errors, @Nullable MetaErrorResponseTemplate meta) {
        this.errors = errors;
        this.meta = meta;
    }

    public ResponseErrorTemplate errors(List<@Valid ResponseErrorsInnerTemplate> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseErrorTemplate addErrorsItem(ResponseErrorsInnerTemplate errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    @NotNull
    @Valid
    @Size(min = 1, max = 13)
    @JsonProperty("errors")
    public List<@Valid ResponseErrorsInnerTemplate> getErrors() {
        return errors;
    }

    public void setErrors(List<@Valid ResponseErrorsInnerTemplate> errors) {
        this.errors = errors;
    }

    public ResponseErrorTemplate meta(MetaErrorResponseTemplate meta) {
        this.meta = meta;
        return this;
    }

    @Valid
    @JsonProperty("meta")
    public MetaErrorResponseTemplate getMeta() {
        return meta;
    }

    public void setMeta(MetaErrorResponseTemplate meta) {
        this.meta = meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseErrorTemplate responseError = (ResponseErrorTemplate) o;
        return Objects.equals(this.errors, responseError.errors) &&
                Objects.equals(this.meta, responseError.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, meta);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseError {\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public static class Builder {

        private ResponseErrorTemplate instance;

        public Builder() {
            this(new ResponseErrorTemplate());
        }

        protected Builder(ResponseErrorTemplate instance) {
            this.instance = instance;
        }

        protected Builder copyOf(ResponseErrorTemplate value) {
            this.instance.setErrors(value.errors);
            this.instance.setMeta(value.meta);
            return this;
        }

        public Builder errors(List<ResponseErrorsInnerTemplate> errors) {
            this.instance.errors(errors);
            return this;
        }

        public Builder meta(MetaErrorResponseTemplate meta) {
            this.instance.meta(meta);
            return this;
        }

        public ResponseErrorTemplate build() {
            try {
                return this.instance;
            } finally {
                // ensure that this.instance is not reused
                this.instance = null;
            }
        }

        @Override
        public String toString() {
            return getClass() + "=(" + instance + ")";
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        return builder.copyOf(this);
    }
}
