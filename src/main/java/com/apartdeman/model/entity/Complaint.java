package com.apartdeman.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**SŞ
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APM_COMPLAINTS")
@EqualsAndHashCode(callSuper = true)
@GenericGenerator(name="TABLE_SEQUENCE",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name="optimizer",value = "none"),
                @org.hibernate.annotations.Parameter(name="initial_value",value = "1"),
                @org.hibernate.annotations.Parameter(name="increment_size",value = "50"),
                @org.hibernate.annotations.Parameter(name=SequenceStyleGenerator.SEQUENCE_PARAM,value = "SEQ_APM_COMPLAINTS"),
        })
public class Complaint extends BaseEntity {

    @Column(name = "TITLE",nullable = false,length = 125)
    private String title;

    @Column(name="DESCR",nullable = false)
    private String descr;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "ST_ID",nullable = false)
    private Long stId;

}
