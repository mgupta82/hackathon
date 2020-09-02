//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.02 at 12:18:00 PM AEST 
//


package com.hcl.hackathon.transfer;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fromAccount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="toAccount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="toAccountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toBsb" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="txnAmount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "messageId",
    "fromAccount",
    "toAccount",
    "toAccountName",
    "toBsb",
    "txnAmount"
})
@XmlRootElement(name = "FundTransferRequest")
public class FundTransferRequest {

    protected int messageId;
    @XmlElement(required = true)
    protected BigInteger fromAccount;
    @XmlElement(required = true)
    protected BigInteger toAccount;
    @XmlElement(required = true)
    protected String toAccountName;
    @XmlElement(required = true)
    protected BigInteger toBsb;
    @XmlElement(required = true)
    protected BigInteger txnAmount;

    /**
     * Gets the value of the messageId property.
     * 
     */
    public int getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     */
    public void setMessageId(int value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the fromAccount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFromAccount() {
        return fromAccount;
    }

    /**
     * Sets the value of the fromAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFromAccount(BigInteger value) {
        this.fromAccount = value;
    }

    /**
     * Gets the value of the toAccount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getToAccount() {
        return toAccount;
    }

    /**
     * Sets the value of the toAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setToAccount(BigInteger value) {
        this.toAccount = value;
    }

    /**
     * Gets the value of the toAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAccountName() {
        return toAccountName;
    }

    /**
     * Sets the value of the toAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAccountName(String value) {
        this.toAccountName = value;
    }

    /**
     * Gets the value of the toBsb property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getToBsb() {
        return toBsb;
    }

    /**
     * Sets the value of the toBsb property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setToBsb(BigInteger value) {
        this.toBsb = value;
    }

    /**
     * Gets the value of the txnAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTxnAmount() {
        return txnAmount;
    }

    /**
     * Sets the value of the txnAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTxnAmount(BigInteger value) {
        this.txnAmount = value;
    }

}
