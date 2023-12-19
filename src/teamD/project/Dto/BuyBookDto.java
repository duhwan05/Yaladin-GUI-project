package teamD.project.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
@Setter
@NoArgsConstructor
public class BuyBookDto {
	private String buy_no;
	private int cart_no;
	private int total;
	private Date order_date;

}

