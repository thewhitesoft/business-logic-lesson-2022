package com.thewhite.demo.service;

import com.thewhite.demo.model.User;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class CreateTaskArgument {

    String title;

    String content;

    LocalDateTime deadline;

    boolean urgency;

    User executor;

    Map<String, Boolean> checkList;
}
