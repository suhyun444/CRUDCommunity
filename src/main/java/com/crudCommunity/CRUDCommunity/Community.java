package com.crudCommunity.CRUDCommunity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="communityinfo")
public class Community
{
    @Id
    @Column(name="user")
    public Long user;
    @Column(name="post_amount")
    public Long postAmount;
    @Column(name="comment_amount")
    public Long commentAmount;
}