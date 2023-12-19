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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
	private int cust_no;
	private String cust_id;
	private String cust_name;
	private int age;
	private String address;
	private String tel;
	private String gender;
	
}
 