package com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JacksonXmlRootElement(localName = "channel")
public class Channel {
    @JacksonXmlProperty(localName = "title")
    @JsonProperty("title")
    String title;

    @JacksonXmlProperty(localName = "ttl")
    @JsonProperty("ttl")
    String ttl;

    @JacksonXmlProperty(localName = "link")
    @JsonProperty("link")
    String link;

    @JacksonXmlProperty(localName = "description")
    @JsonProperty("description")
    String description;

    @JacksonXmlProperty(localName = "copyright")
    @JsonProperty("copyright")
    String copyright;

    @JacksonXmlProperty(localName = "language")
    @JsonProperty("language")
    String language;

    @JacksonXmlProperty(localName = "pubDate")
    @JsonProperty("pubDate")
    String pubDate;

    @JacksonXmlProperty(isAttribute = false, localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Item> item;
}
