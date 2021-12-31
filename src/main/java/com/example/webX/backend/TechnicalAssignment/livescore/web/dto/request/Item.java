package com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName="item")
public class Item implements Serializable {
    @JacksonXmlProperty(localName="title")
    @JsonProperty("title")
    String title;

    @JacksonXmlProperty(localName="link")
    @JsonProperty("link")
    String link;

    @JacksonXmlProperty(localName="description")
    @JsonProperty("description")
    String description;

    @JacksonXmlProperty(localName="guid")
    @JsonProperty("guid")
    String guid;
}
