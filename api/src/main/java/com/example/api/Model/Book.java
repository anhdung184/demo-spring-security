package com.example.api.Model;

import jakarta.persistence.*;

@Entity
@Table(name="BOOK")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BID")
    private long id;
    
    @Column(name = "BNAME")
    private String bname;

    @Column(name = "BAUTHOR")
    private String bauthor;
    
    @Column(name = "BCOST")
    private int bcost;
    
    @Column(name = "BSTATUS")
    private String bstatus;

    public Book(){

    }
    
    public Book(String bname, String bauthor, int bcost, String bstatus){
        this.bname = bname;
        this.bauthor = bauthor;
        this.bcost = bcost;
        this.bstatus = bstatus;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getBname(){
        return bname;
    }
    public void setBname(String bname){
        this.bname = bname;
    }

    public String getBauthor(){
        return bauthor;
    }
    public void setBauthor(String bauthor){
        this.bauthor = bauthor;
    }

    public Integer getBcost(){
        return bcost;
    }
    public void setBcost(int bcost){
        this.bcost = bcost;
    }
    
    public String getBstatus(){
        return bstatus;
    }
    public void setBstatus(String bstatus){
        this.bstatus = bstatus;
    }

    @Override
    public String toString(){
        return "Book [id=" + id + ", bname=" + bname +", bauthor=" + bauthor + ", bcost=" + bcost +", bstatus"+bstatus+ "]";
    }

}