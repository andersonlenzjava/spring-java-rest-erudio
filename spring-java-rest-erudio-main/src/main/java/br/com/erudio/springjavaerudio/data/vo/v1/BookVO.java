package br.com.erudio.springjavaerudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
@JsonPropertyOrder({"id", "author", "launch_date", "price", "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String author;

    @JsonProperty("launch_date")
    private LocalDate launchDate;

    private BigDecimal price;

    private String title;

    public BookVO () {
    }

    public Long getKey() {
        return key;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Objects.equals(key, bookVO.key) && Objects.equals(author, bookVO.author) && Objects.equals(launchDate, bookVO.launchDate) && Objects.equals(price, bookVO.price) && Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, launchDate, price, title);
    }
}
