/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcin.wloch
 */
@XmlRootElement
public class RecordResponse {
    private Integer recID;

    public RecordResponse(Integer recID) {
        this.recID = recID;
    }

    public RecordResponse() {
    }

    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }
    
}
