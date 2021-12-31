package com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ScoreRequest implements Serializable {
    @JacksonXmlProperty(localName = "version")
    @JsonProperty("version")
    String version;

    @JacksonXmlProperty(localName = "channel")
    @JsonProperty("channel")
    Channel channel;
}
