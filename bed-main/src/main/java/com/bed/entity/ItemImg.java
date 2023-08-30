package com.bed.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="item_img")
@Getter
@Setter
public class ItemImg  extends BaseEntity {
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imgName; //이미지 파일명
    private String oriImgName; //원본 이미지 파일명
    private String imgUrl; //이미지 조회 경로
    private String repimgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
    //이미지파일명, 업데이트할 이미지 파일명, 이미지 경로를 입력받아
    //이미지 경로를 업데이트 하는 메소드

}
