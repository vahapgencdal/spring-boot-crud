package com.apartdeman.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 1.09.2018
 * @description TODO: Class Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseRequest implements Serializable {

    private Long id;

    private Long cUser;

    private LocalDateTime cDate;

    private Long uUser;

    private LocalDateTime uDate;

    private Long version;

    private Boolean isActv;

}
