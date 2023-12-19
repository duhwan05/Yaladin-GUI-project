package teamD.project.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@ToString
@Setter
@NoArgsConstructor
public class CartBookDto{
   private int cart_no;
   private int cust_no;
   private String book_name;
   private String author;
   private int price;
   private String new_book_code;
   private String used_book_code;
   private int cnt;
   private String status;
   private int count;
   
}