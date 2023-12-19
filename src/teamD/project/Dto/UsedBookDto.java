package teamD.project.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsedBookDto {
   private String used_book_code;	
   private int cust_no;
   private String book_name;
   private String category;
   private String publisher;
   private String author;
   private int used_price;
   private int cnt;
//   private int total; 
   private Date sel_date;
}