package com.nit.bindingclasse;

import java.time.LocalDate;


import lombok.Data;

@Data
public class PostBindingClass {
	
	private String title;
	private String shortDiscription;

	private String content;


	private LocalDate updatedDate;


}
