package com.apartdeman.model.request;

import com.apartdeman.model.entity.Complaint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 1.09.2018
 * @description TODO: Class Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequest extends BaseRequest {

    private String title;

    private String descr;

    private BigDecimal cost;

    private Long stId;

    public Complaint toSave(long userId){

        Complaint complaint=new Complaint();
        complaint.setCUser(userId);
        complaint.setCost(this.cost);
        complaint.setDescr(this.descr);
        complaint.setStId(this.stId);
        complaint.setTitle(this.title);

        return complaint;
    }

    public Complaint toUpdate(long userId){

        Complaint complaint=new Complaint();
        complaint.setCost(this.cost);
        complaint.setDescr(this.descr);
        complaint.setStId(this.stId);
        complaint.setTitle(this.title);

        complaint.setUUser(userId);
        complaint.setIsActv(this.getIsActv());
        complaint.setCDate(this.getCDate());
        complaint.setCUser(this.getCUser());
        complaint.setId(this.getId());
        complaint.setVersion(this.getVersion());

        return complaint;
    }
}
