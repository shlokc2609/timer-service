package com.fquick.timer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fquick.core.util.datetime.converter.JodaDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shlok.chaurasia on 05/03/16.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id"})
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @Version
    @Column(name = "version")
    @JsonIgnore
    private Long version;

    @Column(name = "created_at")
    @Convert(converter = JodaDateTimeConverter.class)
    @CreatedDate
    @JsonProperty("created_at")
    private DateTime createdAt;

    @Column(name = "updated_at")
    @Convert(converter = JodaDateTimeConverter.class)
    @LastModifiedDate
    @JsonProperty("updated_at")
    private DateTime updatedAt;
}
