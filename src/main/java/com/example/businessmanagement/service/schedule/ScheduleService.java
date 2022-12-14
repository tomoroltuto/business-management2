package com.example.businessmanagement.service.schedule;

import com.example.businessmanagement.repository.schedule.ScheduleEntity;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

  ScheduleEntity findById(Long scheduleId);

  List<ScheduleEntity> findScheduleList();

  ScheduleEntity create(int userId, LocalDate workingDate, String place, String workContent,
      int numberOfPeople);

  void update(Long scheduleId, int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople);

  void delete(Long scheduleId);
}
