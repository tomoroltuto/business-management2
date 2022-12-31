package com.example.businessmanagement2.repository.schedule;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {

  Long scheduleid;

  int userid;

  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate workingdate;

  String place;

  String workcontent;

  int numberofpeople;

}