package org.example.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operation {
    @XmlElement
    private String operation;


    public void setOperation(String operation){
        this.operation = operation;
    }

    public String geOperation(){
        return operation;
    }

}
