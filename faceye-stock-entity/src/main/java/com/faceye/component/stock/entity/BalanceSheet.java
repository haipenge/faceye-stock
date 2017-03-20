package com.faceye.component.stock.entity;
/**
*资产负责表(1)
*/
public class BalanceSheet{
/**
*流动资产 (13)
*/
private Ele13 ele13 = new Ele13();
public void setEle13(Ele13 ele13){
this.ele13 =  ele13;
}
public Ele13 getEle13(){
return this.ele13;
}
/**
*非流动资产 (14)
*/
private Ele14 ele14 = new Ele14();
public void setEle14(Ele14 ele14){
this.ele14 =  ele14;
}
public Ele14 getEle14(){
return this.ele14;
}
/**
*流动负债 (15)
*/
private Ele15 ele15 = new Ele15();
public void setEle15(Ele15 ele15){
this.ele15 =  ele15;
}
public Ele15 getEle15(){
return this.ele15;
}
/**
*非流动负债 (16)
*/
private Ele16 ele16 = new Ele16();
public void setEle16(Ele16 ele16){
this.ele16 =  ele16;
}
public Ele16 getEle16(){
return this.ele16;
}
/**
*所有者权益 (17)
*/
private Ele17 ele17 = new Ele17();
public void setEle17(Ele17 ele17){
this.ele17 =  ele17;
}
public Ele17 getEle17(){
return this.ele17;
}
}

