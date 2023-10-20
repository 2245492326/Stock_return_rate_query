package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class timedTaskUtil {
  @Autowired private SqlUtil sqlUtil;

  @Scheduled(cron = "0 0 */1 * * *")
  // 每隔1小时更新一次，同样可用 fixedRate = 3600000 表示
  public void timedTask() {
    sqlUtil.sqlAllUtil();
  }
}
