package com.example.businessmanagement2.controller.advice;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ResourceNotFoundError {

  private String title;

  private String detail;

}
