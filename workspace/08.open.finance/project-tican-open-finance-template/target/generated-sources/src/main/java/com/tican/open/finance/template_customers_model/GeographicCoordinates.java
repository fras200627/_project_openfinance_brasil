package com.tican.open.finance.template_customers_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Conjunto de informações, que correspondem aos valores das coordenadas geográficas em graus decimais, no Sistema de referência WGS84
 */

@Schema(name = "GeographicCoordinates", description = "Conjunto de informações, que correspondem aos valores das coordenadas geográficas em graus decimais, no Sistema de referência WGS84")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:06.541906200-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public class GeographicCoordinates {

  private String latitude;

  private String longitude;

  public GeographicCoordinates() {
    super();
  }

  /**
   * Constructor with only required parameters and all parameters
   */
  public GeographicCoordinates(String latitude, String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public GeographicCoordinates latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Informação da Latitude referente a geolocalização informada. Entre -90 e 90.p.ex. '-23.5475000'. (2 casas antes da vírgula, 11 posições) 
   * @return latitude
   */
  @NotNull @Pattern(regexp = "^-?\\d{1,2}\\.\\d{1,9}$") @Size(max = 13) 
  @Schema(name = "latitude", example = "-23.5475000", description = "Informação da Latitude referente a geolocalização informada. Entre -90 e 90.p.ex. '-23.5475000'. (2 casas antes da vírgula, 11 posições) ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("latitude")
  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public GeographicCoordinates longitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Informação da Longitude referente a geolocalização informada. Entre -180 e 180. p.ex '-46.6361100'. (3 casas antes da vírgula, 11 posições) 
   * @return longitude
   */
  @NotNull @Pattern(regexp = "^-?\\d{1,3}\\.\\d{1,8}$") @Size(max = 13) 
  @Schema(name = "longitude", example = "-46.6361100", description = "Informação da Longitude referente a geolocalização informada. Entre -180 e 180. p.ex '-46.6361100'. (3 casas antes da vírgula, 11 posições) ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("longitude")
  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeographicCoordinates geographicCoordinates = (GeographicCoordinates) o;
    return Objects.equals(this.latitude, geographicCoordinates.latitude) &&
        Objects.equals(this.longitude, geographicCoordinates.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeographicCoordinates {\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private GeographicCoordinates instance;

    public Builder() {
      this(new GeographicCoordinates());
    }

    protected Builder(GeographicCoordinates instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GeographicCoordinates value) { 
      this.instance.setLatitude(value.latitude);
      this.instance.setLongitude(value.longitude);
      return this;
    }

    public GeographicCoordinates.Builder latitude(String latitude) {
      this.instance.latitude(latitude);
      return this;
    }
    
    public GeographicCoordinates.Builder longitude(String longitude) {
      this.instance.longitude(longitude);
      return this;
    }
    
    /**
    * returns a built GeographicCoordinates instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GeographicCoordinates build() {
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

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static GeographicCoordinates.Builder builder() {
    return new GeographicCoordinates.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GeographicCoordinates.Builder toBuilder() {
    GeographicCoordinates.Builder builder = new GeographicCoordinates.Builder();
    return builder.copyOf(this);
  }

}

