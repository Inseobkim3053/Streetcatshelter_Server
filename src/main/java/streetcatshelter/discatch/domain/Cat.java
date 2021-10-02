package streetcatshelter.discatch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import streetcatshelter.discatch.dto.CatRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cat extends TimeStamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String catName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private String catImage;

    @Column(nullable = false)
    private String neutering;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int cntComment;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int cntLikeIt;
    //조회수
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int cntView;

    public void updateCntView(int cntView) {
        this.cntView = cntView;
    }
    public void updateCntComment(int cntComment) {
        this.cntComment = cntComment;
    }

    @OneToMany(mappedBy = "cat", cascade = {CascadeType.REMOVE},orphanRemoval = true)
    private List<CatDetail> catDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "cat", cascade = {CascadeType.REMOVE})
    private List<CatTag> catTagList = new ArrayList<>();

    @OneToMany(mappedBy = "cat", cascade = {CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "cat")
    private List<CatImage> catImages = new ArrayList<>();

    public Cat(CatRequestDto requestDto) {
        this.neutering = requestDto.getNeutering();
        this.catName = requestDto.getCatName();
        this.location = requestDto.getLocation();
        this.username = requestDto.getUsername();
        this.catImage = requestDto.getCatImage();
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
    }

    public void addCatTagList(List<CatTag> catTagList) {
        this.catTagList = catTagList;
    }
}
