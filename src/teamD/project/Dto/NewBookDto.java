package teamD.project.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewBookDto {
   private String new_book_code;
   private String book_name;
   private String category;
   private String author;
   private String publisher;
   private int price;
   private int cnt;
}