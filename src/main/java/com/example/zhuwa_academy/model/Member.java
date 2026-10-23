package com.example.zhuwa_academy.model;

import com.example.zhuwa_academy.enums.MembershipRole;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "members",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"susu_group_id", "name"}
    )
)
  public class Member {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "user_id", nullable = false)
      private User name;


       @Enumerated(EnumType.STRING)
      @Column(nullable = false)
      private MembershipRole role;


      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "susu_group_id", nullable = false)
      private SusuGroup susuGroup;


      @Column(nullable = false)
      private Integer joinOrder;

   

      public Member(User name, SusuGroup susuGroup, Integer joinOrder) {
        this.name = name;
        this.susuGroup = susuGroup;
        this.joinOrder = joinOrder;
    }
}