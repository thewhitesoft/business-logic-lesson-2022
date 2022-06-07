package com.thewhite.demo.action;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class CreateTaskActionArgument {

    String title;

    String content;

    LocalDateTime deadline;

    boolean urgency;

    Map<String, Boolean> checkList;
}
