package com.apartdeman.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@GenericGenerator(name="TABLE_SEQUENCE",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                    parameters = {
                        @org.hibernate.annotations.Parameter(name="optimizer",value = "none"),
                        @org.hibernate.annotations.Parameter(name="initial_value",value = "1"),
                        @org.hibernate.annotations.Parameter(name="increment_size",value = "50"),
                        @org.hibernate.annotations.Parameter(name=SequenceStyleGenerator.SEQUENCE_PARAM,value = "SEQ_BASE_ENT"),
                    })
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TABLE_SEQUENCE")
    @Column(name = "ID",updatable = false,nullable = false)
    private Long id;

    @Column(name = "CUSER",nullable = false)
    private Long cUser;

    @Column(name = "CDATE",nullable = false)
    private LocalDateTime cDate;

    @Column(name = "UUSER")
    private Long uUser;

    @Column(name = "UDATE")
    private LocalDateTime uDate;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "IS_ACTV",nullable = false)
    @Type(type = "numeric_boolean")
    private Boolean isActv;

    @PrePersist
    public void onPrePersist(){
        setCDate(LocalDateTime.now());
        setIsActv(Boolean.TRUE);
    }

    @PreUpdate
    public void onPreUpdate(){
        setUDate(LocalDateTime.now());
    }



}
