package com.ofb.lib.handlers.exception.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class MetaErrorResponseTemplate {

    private String requestDateTime;

    public MetaErrorResponseTemplate() {
        super();
    }

    public MetaErrorResponseTemplate(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public MetaErrorResponseTemplate requestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
        return this;
    }

    @NotNull
    @Size(max = 20)
    @JsonProperty("requestDateTime")
    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetaErrorResponseTemplate metaErrorResponseTemplate = (MetaErrorResponseTemplate) o;
        return Objects.equals(this.requestDateTime, metaErrorResponseTemplate.requestDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDateTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MetaError {\n");
        sb.append("    requestDateTime: ").append(toIndentedString(requestDateTime)).append("\n");
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

        private MetaErrorResponseTemplate instance;

        public Builder() {
            this(new MetaErrorResponseTemplate());
        }

        protected Builder(MetaErrorResponseTemplate instance) {
            this.instance = instance;
        }

        protected Builder copyOf(MetaErrorResponseTemplate value) {
            this.instance.setRequestDateTime(value.requestDateTime);
            return this;
        }

        public Builder requestDateTime(String requestDateTime) {
            this.instance.requestDateTime(requestDateTime);
            return this;
        }

        public MetaErrorResponseTemplate build() {
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
